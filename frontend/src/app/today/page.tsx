"use client";

import { useEffect, useState } from 'react';
import { fetchApi } from '@/lib/api';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/lib/AuthContext';

export default function TodayPage() {
  const [loading, setLoading] = useState(true);
  const { logout } = useAuth();
  const router = useRouter();

  useEffect(() => {
    const checkOnboarding = async () => {
      try {
        const goalRes = await fetchApi('/me/goal');
        if (!goalRes.ok) {
          router.push('/onboarding');
          return;
        }
        setLoading(false);
      } catch (e) {
        router.push('/onboarding');
      }
    };
    checkOnboarding();
  }, [router]);

  if (loading) {
    return <div className="p-8 text-center">Loading your plan...</div>;
  }

  return (
    <div className="min-h-screen p-8 max-w-4xl mx-auto">
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-4xl font-bold">Today's Plan</h1>
        <button 
          onClick={logout}
          className="bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded"
        >
          Logout
        </button>
      </div>

      <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-100 text-center py-12">
        <h2 className="text-2xl font-semibold mb-4">Чтобы построить персональный план, сначала нужно определить твой текущий уровень.</h2>
        <p className="text-gray-500">
          Diagnostic assessment will be available in a future update.
        </p>
      </div>
    </div>
  );
}
