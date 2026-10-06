"use client";

import { useState } from 'react';
import { fetchApi } from '@/lib/api';
import { useRouter } from 'next/navigation';

export default function OnboardingPage() {
  const [language, setLanguage] = useState('RU');
  const [target, setTarget] = useState('40');
  const [dailyMinutes, setDailyMinutes] = useState('30');
  const [error, setError] = useState('');
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    try {
      // 1. Create/Update Profile
      const profileRes = await fetchApi('/me/profile', {
        method: 'PATCH',
        body: JSON.stringify({
          preferredLanguage: language,
          grade: '11',
          timezone: 'Asia/Almaty'
        }),
      });

      if (!profileRes.ok) {
        throw new Error('Failed to save profile');
      }

      // 2. Create/Update Goal
      const goalRes = await fetchApi('/me/goal', {
        method: 'PUT',
        body: JSON.stringify({
          subject: 'MATHEMATICS',
          target: parseInt(target, 10),
          dailyMinutes: parseInt(dailyMinutes, 10)
        }),
      });

      if (!goalRes.ok) {
        throw new Error('Failed to save goal');
      }

      router.push('/today');
    } catch (err: any) {
      setError(err.message || 'An error occurred during onboarding');
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center p-4">
      <div className="w-full max-w-md space-y-8">
        <div className="text-center">
          <h2 className="text-3xl font-bold tracking-tight">Welcome! Let's set up your profile</h2>
        </div>
        <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
          
          <div>
            <label className="block text-sm font-medium text-gray-700">Preferred Language</label>
            <select
              value={language}
              onChange={(e) => setLanguage(e.target.value)}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm pl-2 py-2 border"
            >
              <option value="RU">Русский</option>
              <option value="KK">Қазақша</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">Target Score (1-40)</label>
            <input
              type="number"
              min="1"
              max="40"
              value={target}
              onChange={(e) => setTarget(e.target.value)}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm pl-2 py-2 border"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">Daily Study Minutes</label>
            <input
              type="number"
              min="10"
              max="240"
              value={dailyMinutes}
              onChange={(e) => setDailyMinutes(e.target.value)}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm pl-2 py-2 border"
            />
          </div>

          {error && <p className="text-red-500 text-sm">{error}</p>}

          <div>
            <button
              type="submit"
              className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500"
            >
              Start Learning
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
