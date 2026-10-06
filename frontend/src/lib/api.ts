export const API_BASE_URL = '/api/v1';

function getCsrfToken() {
  const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
  if (match) {
    return match[2];
  }
  return null;
}

export async function fetchApi(endpoint: string, options: RequestInit = {}) {
  const headers = new Headers(options.headers || {});
  
  headers.set('Content-Type', 'application/json');
  
  if (options.method && options.method !== 'GET' && options.method !== 'HEAD') {
    const csrfToken = getCsrfToken();
    if (csrfToken) {
      headers.set('X-XSRF-TOKEN', csrfToken);
    }
  }

  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    ...options,
    headers,
    credentials: 'include', // Important for sending/receiving the session cookie
  });

  return response;
}
