package com.example.administrador.teste.util;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import com.example.administrador.teste.R;

/**
 * Created by Administrador on 22/07/2015.
 */
public final class FormHelper {

    private FormHelper(){
        super();
    }

    public static boolean requiredValidate(Context context, EditText... editTexts){
        // FAZ A VERIFICAÇÃO SE TODOS OS CAMPOS(PARAMETROS) FORAM PREENCHIDOS.
        boolean valid = true;

        for(EditText editText : editTexts){
            String value = editText.getText() == null ? null : editText.getText().toString();

            if(value ==  null || value.trim().isEmpty()) {
                String errorMessage = context.getString(R.string.required_field);
                editText.setError(errorMessage);
                valid = false;
            }
        }

        return valid;
    }

}
