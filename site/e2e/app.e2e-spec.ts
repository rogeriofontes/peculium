import { RestauranteWebAppPage } from './app.po';

describe('restaurante-web-app App', () => {
  let page: RestauranteWebAppPage;

  beforeEach(() => {
    page = new RestauranteWebAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
