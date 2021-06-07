package com.example.day2.slice;

import com.example.day2.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.window.dialog.ToastDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpAbilitySlice extends AbilitySlice {
    private int flag1=0, flag2=0, flag3=0, flag4=0, flag5=0;
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_signup);
        TextField fnametext, lnametext, emailtext, passwordtext, mobiletext;
        Text fnameerror, lnameerror, emailerror, passworderror, mobileerror;

        fnametext = (TextField)findComponentById(ResourceTable.Id_FirstNameText);
        fnameerror = (Text)findComponentById(ResourceTable.Id_fnameError);
        fnametext.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                if(s.length()==0)
                {
                    fnameerror.setText("Please enter a valid first name");
                    flag1 = 0;
                }
                else if(!s.matches("^[a-zA-Z]*$"))
                {
                    fnameerror.setText("Invalid entry: Name contains only alphabets");
                    flag1 = 0;
                }
                else
                {
                    fnameerror.setText("");
                    flag1 = 1;
                }
            }
        });

        lnametext = (TextField)findComponentById(ResourceTable.Id_LastNameText);
        lnameerror = (Text)findComponentById(ResourceTable.Id_lastNameError);
        lnametext.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                if(s.length()==0)
                {
                    lnameerror.setText("Please enter a valid last name");
                    flag2 = 0;
                }
                else if(!s.matches("^[a-zA-Z]*$"))
                {
                    lnameerror.setText("Invalid entry: Name contains only alphabets");
                    flag2 = 0;
                }
                else
                {
                    lnameerror.setText("");
                    flag2 = 1;
                }
            }
        });

        emailtext = (TextField)findComponentById(ResourceTable.Id_EmailText);
        emailerror = (Text)findComponentById(ResourceTable.Id_EmailTextError);
        emailtext.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                if (s.length() == 0)
                {
                    emailerror.setText("Please enter a valid email address");
                    flag3 = 0;
                }
                else if (!s.matches("^(.+)@(.+)$"))
                {
                    emailerror.setText("Invalid entry: Email is of the form username@domain");
                    flag3 = 0;
                }
                else
                {
                    emailerror.setText("");
                    flag3 = 1;
                }
            }
        });

        passwordtext = (TextField)findComponentById(ResourceTable.Id_PasswordText);
        passworderror = (Text)findComponentById(ResourceTable.Id_PasswordError);
        passwordtext.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                Pattern p = Pattern.compile("[^A-Za-z0-9]");
                Matcher m = p.matcher(passwordtext.getText());
                if (s.length() == 0)
                {
                    passworderror.setText("Please enter a valid password");
                    flag4 = 0;
                }
                else if(!m.find())
                {
                    passworderror.setText("Invalid entry: Password must contain alphabet and special character");
                    flag4 = 0;
                }
                else
                {
                    passworderror.setText("");
                    flag4 = 1;
                }
            }
        });

        mobiletext = (TextField)findComponentById(ResourceTable.Id_MobileText);
        mobileerror = (Text)findComponentById(ResourceTable.Id_MobileError);
        mobiletext.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                if (s.length() == 0)
                {
                    mobileerror.setText("Please enter a valid mobile number");
                    flag5 = 0;
                }
                else if (!s.matches("\\d{10}"))
                {
                    mobileerror.setText("Invalid entry: Phone Number must contain 10 digits");
                    flag5 = 0;
                }
                else
                {
                    mobileerror.setText("");
                    flag5 = 1;
                }
            }
        });

        RadioButton malebtn = (RadioButton)findComponentById(ResourceTable.Id_MaleButton);
        RadioButton femalebtn = (RadioButton)findComponentById(ResourceTable.Id_FemaleButton);
        malebtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                femalebtn.setChecked(false);
            }
        });

        femalebtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                malebtn.setChecked(false);
            }
        });
        Button signUp = (Button)findComponentById(ResourceTable.Id_signUp2);
        signUp.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if(fnametext.getText().isEmpty() || lnametext.getText().isEmpty() || emailtext.getText().isEmpty() || passwordtext.getText().isEmpty() || mobiletext.getText().isEmpty() || flag1==0 || flag2==0 || flag3==0 || flag4==0 || flag5==0){
                    new ToastDialog(getContext())
                            .setText("Please enter valid inputs")
                            .show();
                }
                else if(!malebtn.isChecked() && !femalebtn.isChecked())
                {
                    new ToastDialog(getContext())
                            .setText("Please check one gender")
                            .show();
                }
                else
                {
                    new ToastDialog(getContext())
                            .setText("Data Entry Success")
                            .show();
                }
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}