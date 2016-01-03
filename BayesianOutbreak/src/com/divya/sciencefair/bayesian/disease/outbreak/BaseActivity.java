/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.divya.sciencefair.bayesian.disease.outbreak;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseActivity extends FragmentActivity {
    protected TextView fever_tv, headache_tv;
    protected Integer fever_val, headache_val;

    protected int getLayoutId() {
        return R.layout.symptoms;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        headache_tv = (TextView) findViewById(R.id.headache_dt);
        fever_tv = (TextView) findViewById(R.id.fever_dt);

        String curr_dt = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        fever_tv.setText(curr_dt);
        headache_tv.setText(curr_dt);

        fever_val=0;
        headache_val=0;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onFeverRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.fever1:
                if (checked) {
                    fever_val=0;

                    hideDtText(fever_tv);
                }
                else showDtText(fever_tv);
                break;
            case R.id.fever2:
                if (checked) {
                    fever_val=1;
                    showDtText(fever_tv);
                }
                else hideDtText(fever_tv);
                break;
            case R.id.fever3:
                if (checked) {
                    fever_val=2;
                    showDtText(fever_tv);
                }
                else hideDtText(fever_tv);
                break;
            case R.id.fever4:
                if (checked) {
                    fever_val=3;
                    showDtText(fever_tv);
                }
                else hideDtText(fever_tv);
                break;
            case R.id.fever5:
                if (checked) {
                    fever_val=4;
                    showDtText(fever_tv);
                }
                else hideDtText(fever_tv);
                break;

            default:
                if (checked) showDtText(fever_tv);
                else hideDtText(fever_tv);
                break;
        }
    }

    public void onHeadacheRadioButtonClicked(View view) {
         // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()) {
                case R.id.headache1:
                    if (checked) {
                        headache_val=0;
                        hideDtText(headache_tv);
                    }
                    else showDtText(headache_tv);
                    break;
                case R.id.headache2:
                    if (checked) {
                        headache_val=1;
                        showDtText(headache_tv);
                    }
                    else hideDtText(headache_tv);
                    break;
                case R.id.headache3:
                    if (checked) {
                        headache_val=2;
                        showDtText(headache_tv);
                    }
                    else hideDtText(headache_tv);
                    break;
                case R.id.headache4:
                    if (checked) {
                        headache_val=3;
                        showDtText(headache_tv);
                    }
                    else hideDtText(headache_tv);
                    break;
                case R.id.headache5:
                    if (checked) {
                        headache_val=4;
                        showDtText(headache_tv);
                    }
                    else hideDtText(headache_tv);
                    break;

                default:
                    if (checked) showDtText(headache_tv);
                    else hideDtText(headache_tv);
                    break;
            }
        }
        /**
         * Run the demo-specific code.
         */

    private void hideDtText(TextView textView) {
        textView.setFocusable(false);
        textView.setClickable(false);
        textView.setFocusableInTouchMode(false);
        textView.setTextColor(Color.TRANSPARENT);
    }

    private void showDtText(TextView textView) {
        textView.setFocusable(true);
        textView.setClickable(true);
        textView.setFocusableInTouchMode(true);
        textView.setTextColor(Color.BLACK);
    }
    protected abstract void startDemo();


}
