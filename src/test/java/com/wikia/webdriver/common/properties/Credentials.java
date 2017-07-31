package com.wikia.webdriver.common.properties;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.EditorPref;

import java.io.File;

public class Credentials {

  public final String youTubeApiKey;
  public final String userName;
  public final String password;

  public final String userName2;
  public final String password2;

  public final String userName3;
  public final String password3;

  public final String userName4;
  public final String password4;

  public final String userName5;
  public final String password5;

  public final String userName6;
  public final String password6;

  public final String userName7;
  public final String password7;

  public final String userName8;
  public final String password8;

  public final String userName9;
  public final String password9;

  public final String userName10;
  public final String password10;

  public final String userName11;
  public final String password11;

  public final String userName12;
  public final String password12;

  public final String userName13;
  public final String password13;

  public final String userNameClosedAccount;
  public final String passwordClosedAccount;

  public final String userNameBlockedAccount;
  public final String passwordBlockedAccount;

  public final String email;
  public final String emailPassword;
  public final String emailQaart1;
  public final String emailPasswordQaart1;
  public final String emailQaart2;
  public final String emailPasswordQaart2;
  public final String emailQaart4;
  public final String emailPasswordQaart4;
  public final String forgottenPasswordEmail1Address;
  public final String forgottenPasswordEmail1Password;

  public final String forgottenPasswordEmail2Address;
  public final String forgottenPasswordEmail2Password;

  public final String userNameStaff;
  public final String userNameStaffId;
  public final String passwordStaff;

  public final String userNameStaff2;
  public final String passwordStaff2;

  public final String userNameMonobook;
  public final String passwordMonobook;

  public final String userNameFB;
  public final String passwordFB;
  public final String emailFB;

  public final String userNameBlocked;
  public final String passwordBlocked;

  public final String userNameForgottenPassword;
  public final String userNameForgottenPassword2;
  public final String userNameForgottenPassword3;

  public final String userNameVEPreferred;
  public final String passwordVEPreferred;

  public final String userNameCKPreferred;
  public final String passwordCKPreferred;

  public final String userNameSourcePreferred;
  public final String passwordSourcePreferred;

  public final String userNameDefaultPreferred;
  public final String passwordDefaultPreferred;

  public final String userNameGoSearchPreferred;
  public final String passwordGoSearchPreferred;

  public final String userNameEnglish;
  public final String passwordEnglish;

  public final String userNameJapanese;
  public final String passwordJapanese;

  public final String userNameJapanese2;
  public final String passwordJapanese2;

  public final String userNameBrazilianPortuguese;
  public final String passwordBrazilianPortuguese;

  public final String userNameChinese;
  public final String passwordChinese;

  public final String userNameGerman;
  public final String passwordGerman;

  public final String userNameFrench;
  public final String passwordFrench;

  public final String userNameSpanish;
  public final String passwordSpanish;

  public final String userNameRussian;
  public final String passwordRussian;

  public final String userNamePolish;
  public final String passwordPolish;

  public final String userNameItalian;
  public final String passwordItalian;

  public final String apiToken;

  public Credentials() {
    File file = new File(Configuration.getCredentialsFilePath());
    userName = XMLReader.getValue(file, "ci.user.regular.username");
    password = XMLReader.getValue(file, "ci.user.regular.password");
    userName2 = XMLReader.getValue(file, "ci.user.regular2.username");
    password2 = XMLReader.getValue(file, "ci.user.regular2.password");
    userName3 = XMLReader.getValue(file, "ci.user.regular3.username");
    password3 = XMLReader.getValue(file, "ci.user.regular3.password");
    userName4 = XMLReader.getValue(file, "ci.user.regular4.username");
    password4 = XMLReader.getValue(file, "ci.user.regular4.password");
    userName5 = XMLReader.getValue(file, "ci.user.regular5.username");
    password5 = XMLReader.getValue(file, "ci.user.regular5.password");
    userName6 = XMLReader.getValue(file, "ci.user.regular6.username");
    password6 = XMLReader.getValue(file, "ci.user.regular6.password");
    userName7 = XMLReader.getValue(file, "ci.user.regular7.username");
    password7 = XMLReader.getValue(file, "ci.user.regular7.password");
    userName8 = XMLReader.getValue(file, "ci.user.regular8.username");
    password8 = XMLReader.getValue(file, "ci.user.regular8.password");
    userName9 = XMLReader.getValue(file, "ci.user.regular9.username");
    password9 = XMLReader.getValue(file, "ci.user.regular9.password");
    userName10 = XMLReader.getValue(file, "ci.user.regular10.username");
    password10 = XMLReader.getValue(file, "ci.user.regular10.password");
    userName11 = XMLReader.getValue(file, "ci.user.regular11.username");
    password11 = XMLReader.getValue(file, "ci.user.regular11.password");
    userName12 = XMLReader.getValue(file, "ci.user.regular12.username");
    password12 = XMLReader.getValue(file, "ci.user.regular12.password");
    userName13 = XMLReader.getValue(file, "ci.user.regular13.username");
    password13 = XMLReader.getValue(file, "ci.user.regular13.password");

    userNameEnglish = XMLReader.getValue(file, "ci.user.language1.username");
    passwordEnglish = XMLReader.getValue(file, "ci.user.language1.password");
    userNameJapanese = XMLReader.getValue(file, "ci.user.language2.username");
    passwordJapanese = XMLReader.getValue(file, "ci.user.language2.password");
    userNameBrazilianPortuguese = XMLReader.getValue(file, "ci.user.language3.username");
    passwordBrazilianPortuguese = XMLReader.getValue(file, "ci.user.language3.password");
    userNameChinese = XMLReader.getValue(file, "ci.user.language4.username");
    passwordChinese = XMLReader.getValue(file, "ci.user.language4.password");
    userNameGerman = XMLReader.getValue(file, "ci.user.language5.username");
    passwordGerman = XMLReader.getValue(file, "ci.user.language5.password");
    userNameFrench = XMLReader.getValue(file, "ci.user.language6.username");
    passwordFrench = XMLReader.getValue(file, "ci.user.language6.password");
    userNameSpanish = XMLReader.getValue(file, "ci.user.language7.username");
    passwordSpanish = XMLReader.getValue(file, "ci.user.language7.password");
    userNameRussian = XMLReader.getValue(file, "ci.user.language8.username");
    passwordRussian = XMLReader.getValue(file, "ci.user.language8.password");
    userNamePolish = XMLReader.getValue(file, "ci.user.language9.username");
    passwordPolish = XMLReader.getValue(file, "ci.user.language9.password");
    userNameItalian = XMLReader.getValue(file, "ci.user.language10.username");
    passwordItalian = XMLReader.getValue(file, "ci.user.language10.password");
    userNameJapanese2 = XMLReader.getValue(file, "ci.user.language11.username");
    passwordJapanese2 = XMLReader.getValue(file, "ci.user.language11.password");

    userNameClosedAccount = XMLReader.getValue(file, "ci.user.closeAccountUser.username");
    passwordClosedAccount = XMLReader.getValue(file, "ci.user.closeAccountUser.password");

    userNameBlockedAccount = XMLReader.getValue(file, "ci.user.blockedAccountUser.username");
    passwordBlockedAccount = XMLReader.getValue(file, "ci.user.blockedAccountUser.password");

    userNameStaff = XMLReader.getValue(file, "ci.user.wikiastaff.username");
    userNameStaffId = XMLReader.getValue(file, "ci.user.wikiastaff.id");
    passwordStaff = XMLReader.getValue(file, "ci.user.wikiastaff.password");

    userNameStaff2 = XMLReader.getValue(file, "ci.user.wikiastaff2.username");
    passwordStaff2 = XMLReader.getValue(file, "ci.user.wikiastaff2.password");

    userNameMonobook = XMLReader.getValue(file, "ci.user.wikiamonobook.username");
    passwordMonobook = XMLReader.getValue(file, "ci.user.wikiamonobook.password");

    emailFB = XMLReader.getValue(file, "ci.user.facebook.email");
    passwordFB = XMLReader.getValue(file, "ci.user.facebook.password");
    userNameFB = XMLReader.getValue(file, "ci.user.facebook.username");

    email = XMLReader.getValue(file, "ci.email.generic.username");
    emailPassword = XMLReader.getValue(file, "ci.email.generic.password");

    emailQaart1 = XMLReader.getValue(file, "ci.email.qawikia1.username");
    emailPasswordQaart1 = XMLReader.getValue(file, "ci.email.qawikia1.password");
    emailQaart2 = XMLReader.getValue(file, "ci.email.qawikia2.username");
    emailPasswordQaart2 = XMLReader.getValue(file, "ci.email.qawikia2.password");
    emailQaart4 = XMLReader.getValue(file, "ci.email.qawikia4.username");
    emailPasswordQaart4 = XMLReader.getValue(file, "ci.email.qawikia4.password");

    forgottenPasswordEmail1Address = XMLReader.getValue(file, "ci.email.forgotPass1.username");
    forgottenPasswordEmail1Password = XMLReader.getValue(file, "ci.email.forgotPass1.password");

    forgottenPasswordEmail2Address = XMLReader.getValue(file, "ci.email.forgotPass2.username");
    forgottenPasswordEmail2Password = XMLReader.getValue(file, "ci.email.forgotPass2.password");

    userNameBlocked = XMLReader.getValue(file, "ci.user.tooManyLoginAttempts.username");
    passwordBlocked = XMLReader.getValue(file, "ci.user.tooManyLoginAttempts.password");

    userNameForgottenPassword = XMLReader.getValue(file, "ci.user.forgottenPassword.username1");
    userNameForgottenPassword2 = XMLReader.getValue(file, "ci.user.forgottenPassword.username2");
    userNameForgottenPassword3 = XMLReader.getValue(file, "ci.user.forgottenPassword.username3");

    userNameVEPreferred = XMLReader.getValue(file, "ci.user.vePreferredUser.username");
    passwordVEPreferred = XMLReader.getValue(file, "ci.user.vePreferredUser.password");

    userNameCKPreferred = XMLReader.getValue(file, "ci.user.ckPreferredUser.username");
    passwordCKPreferred = XMLReader.getValue(file, "ci.user.ckPreferredUser.password");

    userNameSourcePreferred = XMLReader.getValue(file, "ci.user.sourcePreferredUser.username");
    passwordSourcePreferred = XMLReader.getValue(file, "ci.user.sourcePreferredUser.password");

    userNameDefaultPreferred = XMLReader.getValue(file, "ci.user.defaultPreferredUser.username");
    passwordDefaultPreferred = XMLReader.getValue(file, "ci.user.defaultPreferredUser.password");

    userNameGoSearchPreferred = XMLReader.getValue(file, "ci.user.goSearchPreferredUser.username");
    passwordGoSearchPreferred = XMLReader.getValue(file, "ci.user.goSearchPreferredUser.password");

    apiToken = XMLReader.getValue(file, "ci.api.token");

    youTubeApiKey = XMLReader.getValue(file, "ci.api.youtube.key");
  }

  public String getUserBaseOnEditorPref(EditorPref editorPref) {
    switch (editorPref) {
      case VE:
        return userNameVEPreferred;
      case CK:
        return userNameCKPreferred;
      case SRC:
        return userNameSourcePreferred;
      default:
        return "";
    }
  }

  public String getPassBaseOnEditorPref(EditorPref editorPref) {
    switch (editorPref) {
      case VE:
        return passwordVEPreferred;
      case CK:
        return passwordCKPreferred;
      case SRC:
        return passwordSourcePreferred;
      default:
        return "";
    }
  }
}
