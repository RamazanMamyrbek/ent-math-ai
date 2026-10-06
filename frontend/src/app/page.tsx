"use client";

import { useEffect, useState } from "react";

interface SystemStatus {
  status: string;
  database: string;
}

export default function Home() {
  const [status, setStatus] = useState<SystemStatus | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch('/api/v1/system/status')
      .then(res => {
        if (!res.ok) throw new Error("Failed to fetch");
        return res.json();
      })
      .then(data => setStatus(data))
      .catch(err => setError(err.message));
  }, []);

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-4xl font-bold mb-8">ENT Math AI System Status</h1>
      
      <div className="p-6 border rounded-lg shadow-sm bg-white dark:bg-gray-800 text-center">
        {error ? (
          <p className="text-red-500">Error: {error}</p>
        ) : status ? (
          <div>
            <p className="text-lg">System: <span className="font-semibold text-green-600" data-testid="system-status">{status.status}</span></p>
            <p className="text-lg mt-2">Database: <span className="font-semibold text-green-600" data-testid="db-status">{status.database}</span></p>
          </div>
        ) : (
          <p>Loading...</p>
        )}
      </div>
    </main>
  );
}
