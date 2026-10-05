import { test, expect } from '@playwright/test';

test('has title and displays system status', async ({ page }) => {
  await page.goto('/');

  // Check the title exists
  await expect(page.getByRole('heading', { name: /ENT Math AI System Status/i })).toBeVisible();

  // Next.js should proxy to backend. If the backend is running, it returns UP. 
  // Wait for the fetch to complete and UI to update
  const systemStatus = page.getByTestId('system-status');
  const dbStatus = page.getByTestId('db-status');

  await expect(systemStatus).toHaveText('UP', { timeout: 10000 });
  await expect(dbStatus).toHaveText('UP', { timeout: 10000 });
});
