package com.myself.library.listener;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Malik J on 2017/12/11.
 */

public abstract class OnConfirmClickListener implements SweetAlertDialog.OnSweetClickListener {
    @Override
    public void onClick(SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.dismissWithAnimation();
        sweetAlertDialog.cancel();
    }
}
