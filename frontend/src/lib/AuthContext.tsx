"use client";

import { createContext, useContext, useEffect, useState, ReactNode } from 'react';
import { fetchApi } from './api';
import { useRouter, usePathname } from 'next/navigation';

interface AuthContextType {
  isAuthenticated: boolean;
  userId: string | null;
  loading: boolean;
  logout: () => void;
  checkSession: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType>({
  isAuthenticated: false,
  userId: null,
  loading: true,
  logout: () => {},
  checkSession: async () => {},
});

export function AuthProvider({ children }: { children: ReactNode }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [userId, setUserId] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);
  const router = useRouter();
  const pathname = usePathname();

  const checkSession = async () => {
    try {
      const res = await fetchApi('/auth/session');
      if (res.ok) {
        const data = await res.json();
        setIsAuthenticated(data.authenticated);
        setUserId(data.userId);
      } else {
        setIsAuthenticated(false);
        setUserId(null);
      }
    } catch (e) {
      setIsAuthenticated(false);
      setUserId(null);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    checkSession();
  }, []);

  useEffect(() => {
    if (!loading) {
      const publicPaths = ['/login', '/register', '/'];
      if (!isAuthenticated && !publicPaths.includes(pathname)) {
        router.push('/login');
      } else if (isAuthenticated && (pathname === '/login' || pathname === '/register')) {
        router.push('/today');
      }
    }
  }, [loading, isAuthenticated, pathname, router]);

  const logout = async () => {
    await fetchApi('/auth/logout', { method: 'POST' });
    setIsAuthenticated(false);
    setUserId(null);
    router.push('/login');
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, userId, loading, logout, checkSession }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
