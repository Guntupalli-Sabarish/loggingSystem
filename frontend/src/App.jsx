import { useState, useEffect, useMemo } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [logs, setLogs] = useState([]);
  const [newLog, setNewLog] = useState('');
  const [searchTerm, setSearchTerm] = useState('');
  const [filterSeverity, setFilterSeverity] = useState('ALL');

  const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

  const fetchLogs = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/api/logs`);
      setLogs(response.data.reverse());
    } catch (error) {
      console.error('Error fetching logs:', error);
    }
  };

  const addSimulatedLog = async () => {
    if (!newLog) return;
    try {
      await axios.post(`${API_BASE_URL}/api/logs`, {
        data: newLog,
        severity: 'LOW',
        threadName: 'UI-Thread'
      });
      setNewLog('');
      fetchLogs();
    } catch (error) {
      console.error('Error adding log:', error);
    }
  };

  useEffect(() => {
    fetchLogs();
    const interval = setInterval(fetchLogs, 2000);
    return () => clearInterval(interval);
  }, []);

  // Level 2: Filter Logic
  const filteredLogs = useMemo(() => {
    return logs.filter(log => {
      const matchesSearch = log.data.toLowerCase().includes(searchTerm.toLowerCase());
      const matchesSeverity = filterSeverity === 'ALL' || (log.severity || 'LOW') === filterSeverity;
      return matchesSearch && matchesSeverity;
    });
  }, [logs, searchTerm, filterSeverity]);

  return (
    <div className="container">
      <header className="header">
        <h1>System Monitor</h1>
        <div className="status-badge">Live</div>
      </header>

      <div className="controls-card">
        <div className="input-group">
          <input
            type="text"
            value={newLog}
            onChange={(e) => setNewLog(e.target.value)}
            placeholder="Simulate a system log..."
            className="log-input"
            onKeyDown={(e) => e.key === 'Enter' && addSimulatedLog()}
          />
          <button onClick={addSimulatedLog} className="add-btn">Add Log</button>
        </div>

        <div className="filters">
          <input
            type="text"
            placeholder="Search logs..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search-input"
          />
          <select
            value={filterSeverity}
            onChange={(e) => setFilterSeverity(e.target.value)}
            className="filter-select"
          >
            <option value="ALL">All Severities</option>
            <option value="HIGH">High</option>
            <option value="WARN">Warn</option>
            <option value="LOW">Low</option>
          </select>
        </div>
      </div>

      <div className="logs-container">
        <h3>Recent Logs ({filteredLogs.length})</h3>
        {filteredLogs.length === 0 ? (
          <div className="empty-state">No matching logs found.</div>
        ) : (
          filteredLogs.map((log, index) => (
            <div key={index} className="log-card">
              <div className="log-meta">
                <span className="timestamp">{new Date(log.timestamp).toLocaleTimeString()}</span>
                <span className={`severity ${log.severity ? log.severity.toLowerCase() : 'low'}`}>
                  {log.severity || 'LOW'}
                </span>
                <span className="thread">[{log.threadName || 'main'}]</span>
              </div>
              <div className="log-message">{log.data}</div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default App;
