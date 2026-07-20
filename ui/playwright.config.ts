import { defineConfig, devices } from '@playwright/test';

export default defineConfig({

  testDir: './e2e',

  fullyParallel: true,

  forbidOnly: !!process.env.CI,

  retries: process.env.CI ? 2 : 0,

  workers: process.env.CI ? 1 : undefined,


  // HTML report
  reporter: [
    ['html', { 
      outputFolder: 'playwright-report',
      open: 'never'
    }]
  ],


  use: {

    // Base URL
    baseURL: 'https://www.flipkart.com',


    // Screenshot evidence
    screenshot: 'on',


    // Video evidence
    video: 'on',


    // Trace evidence
    trace: 'on',


    // Browser settings
    headless: false,

    viewport: {
      width: 1280,
      height: 720
    },

    actionTimeout: 10000,

  },


  projects: [

    {
      name: 'chromium',
      use: {
        ...devices['Desktop Chrome']
      },
    },


    // Enable later if needed
    /*
    {
      name: 'firefox',
      use: {
        ...devices['Desktop Firefox']
      },
    },

    {
      name: 'webkit',
      use: {
        ...devices['Desktop Safari']
      },
    },
    */

  ],


});