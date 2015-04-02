package com.wikia.webdriver.testcases.heromoduletests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import org.testng.annotations.Test;

/**
 * Created by RodriGomez on 02/04/15.
 * Set of Test Cases found on https://wikia-inc.atlassian.net/browse/DAT-2563
 *
 * TC18: Add a new MoM image, refresh the page and verify it is still visible
 * TC04: upload a new image and verify it is displayed immediately
 * TC09: Make,publish and verify changes for description fields are visible immediately
 * TC15: check only admins and staff can modify MoM
 * TC06: verify discarding an image will display previous state
 */
public class HeroModuleTests extends NewTestTemplate{

  Credentials credentials = config.getCredentials();

  private final static String WIKI_NAME = "momrod2";

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_001"})
  public void HeroModuleTests_001_VerifyImageVisibilityAfterRefresh() {

  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_002"})
  public void HeroModuleTests_002_VerifyImmediateImageVisibility() {

  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_003"})
  public void HeroModuleTests_003_VerifyImmediateChangesForDescription() {

  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_004"})
  public void HeroModuleTests_004_OnlyAdminsAndStaffCanModify() {

  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_005"})
  public void HeroModuleTests_005_VerifyImageDiscardDisplaysPreviousState() {

  }

}
