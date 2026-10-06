import { test, expect } from '@playwright/test';

test('register, onboarding, and session persistence', async ({ page, context }) => {
  const email = `test-${Date.now()}@test.com`;
  const password = 'password123';

  // 1. Register
  await page.goto('/register');
  await page.fill('input[type="email"]', email);
  await page.fill('input[type="password"]', password);
  await page.click('button[type="submit"]');

  // Should redirect to onboarding
  await page.waitForURL('/onboarding');
  expect(page.url()).toContain('/onboarding');

  // 2. Onboarding
  await page.selectOption('select', 'RU');
  await page.fill('input[type="number"]:first-of-type', '35'); // Target
  await page.fill('input[type="number"]:last-of-type', '60'); // Daily minutes
  await page.click('button[type="submit"]');

  // Should redirect to today
  await page.waitForURL('/today');
  expect(page.url()).toContain('/today');

  // 3. Reload and check session persists
  await page.reload();
  await page.waitForURL('/today');
  expect(page.url()).toContain('/today');

  // 4. Logout
  await page.click('text=Logout');
  await page.waitForURL('/login');
  expect(page.url()).toContain('/login');

  // 5. Try accessing protected route
  await page.goto('/today');
  await page.waitForURL('/login');
  expect(page.url()).toContain('/login');

  // 6. Login again
  await page.fill('input[type="email"]', email);
  await page.fill('input[type="password"]', password);
  await page.click('button[type="submit"]');
  await page.waitForURL('/today');
  expect(page.url()).toContain('/today');
});
