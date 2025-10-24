import { ApplicationConfig } from '@angular/core';
import { providePrimeNG } from 'primeng/config';

import Aura from '@primeuix/themes/aura';
import {provideHttpClient} from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    providePrimeNG({
      theme: {
        preset: Aura,
        options: {
          prefix: 'p',
          darkModeSelector: 'system',
          cssLayer: false
        }
      }
    }),
    provideHttpClient()
  ]
};
