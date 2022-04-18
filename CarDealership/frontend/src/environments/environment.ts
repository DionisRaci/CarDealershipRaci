import { default as auth } from '../../auth_config.json';

export const environment = {
  baseApiUrl: "http://localhost:8080/CarDealershipAPI/",
  production: false,
  auth: {
    domain: auth.domain,
    clientId: auth.clientId,
    redirectUri: window.location.origin
  }
};

