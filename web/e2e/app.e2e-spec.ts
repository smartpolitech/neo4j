import { SmartPolitechPage } from './app.po';

describe('smart-politech App', () => {
  let page: SmartPolitechPage;

  beforeEach(() => {
    page = new SmartPolitechPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
