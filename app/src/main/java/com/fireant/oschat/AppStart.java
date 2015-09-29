package com.fireant.oschat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.fireant.oschat.bean.LoginUser;
import com.fireant.oschat.utils.ActivityUtils;
import com.fireant.oschat.view.LoginActivity;
import com.fireant.oschat.view.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 启动界面
 * Created by zhangdeyi on 15/7/23.
 */
public class AppStart extends AppCompatActivity {

    @Bind(R.id.iv_welcome)
    ImageView ivWelcome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_start);
        ButterKnife.bind(this);
        AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
        animation.setDuration(3000);
        ivWelcome.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void checkLogin() {
        boolean isLogin = LoginUser.isLogin(this);
        if (isLogin) {
            ActivityUtils.skipActivity(AppStart.this, MainActivity.class);
        } else {
            ActivityUtils.skipActivity(AppStart.this, LoginActivity.class);
        }

    }
}
