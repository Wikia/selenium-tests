package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

public class ModalSelectorsHelper {
    private final String OASIS_MODAL_CSS = "#blackout_ext-wikia-adEngine-template-modal";
    private final String OASIS_MODAL_CLOSE_CSS = "#ext-wikia-adEngine-template-modal .close";

    private final String MERCURY_MODAL_CSS = ".lightbox-ads";
    private final String MERCURY_MODAL_CLOSE_CSS = ".lightbox-close-wrapper";

    private String modalSelector;
    private String modalCloseSelector;

    public ModalSelectorsHelper(String browser) {
        modalSelector = OASIS_MODAL_CSS;
        modalCloseSelector = OASIS_MODAL_CLOSE_CSS;

        if("CHROMEMOBILEMERCURY".equalsIgnoreCase(browser)) {
            modalSelector = MERCURY_MODAL_CSS;
            modalCloseSelector = MERCURY_MODAL_CLOSE_CSS;
        }
    }

    public String getModalSelector() {
        return modalSelector;
    }

    public String getModalCloseSelector() {
        return modalCloseSelector;
    }
}
