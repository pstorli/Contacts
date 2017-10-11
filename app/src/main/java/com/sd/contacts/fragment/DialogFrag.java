package com.sd.contacts.fragment;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sd.contacts.R;

/**
 * Created by pstorli on 10/3/2017.
 *
 * Use this code to create an alert dialog instead.
 *   AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 *   builder.setView(R.layout.dialog_frag);
 *
 *   android:textColor="?attr/dialogHeaderBackground"
 *
 */
@SuppressWarnings("WeakerAccess")
public class DialogFrag extends DialogFragment
{
    public static final String  DIALOG_MSG           = "dialogMessage";
    public static final String  DIALOG_STYLE_ID      = "dialogStyleId";
    public static final String  DIALOG_SHOW_INPUT    = "dialogShowInput";

    // ClickListener button ids
    public static final int     NEG_CLICK           = R.id.btn_dialog_neg;
    public static final int     NEU_CLICK           = R.id.btn_dialog_neu;
    public static final int     POS_CLICK           = R.id.btn_dialog_pos;

    /**
     * Who to call back when a button is clicked.
     */
    protected ClickListener     listener            = null;
    protected Dialog            dialog              = null;

    /**
     * Interface definition for a callback to be invoked when a dialog button is clicked.
     */
    public interface ClickListener
    {
        /**
         * Called when a button has been clicked.
         *
         * @param btnId The id of the button that was clicked.
         * @param text That the user typed in.
         */
        void clicked (int btnId, String text);
    }

    /**
     * Create an info dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag   - The fragment to associate this dialog with.
     * @param resId  - The message to display.
     */
    public static void info (Fragment frag, int resId)
    {
        info (frag, frag.getContext().getString(resId));
    }

    /**
     * Create an info dialog with this message.
     *
     * Button(S): ok
     *
     * @param  frag   - The fragment to associate this dialog with.
     * @param message - The message to display.
     *
     */
    public static void info (Fragment frag, String message)
    {
        info (frag, message, null);
    }

    /**
     * Create an info dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void info (Fragment frag, String message, ClickListener listener)
    {
        DialogFrag df = create (R.style.InfoStyle, message, listener, false);
        df.show(frag.getFragmentManager(), message);
    }

    /**
     * Create a warning dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     *
     */
    public static void warning (Fragment frag, int resId)
    {
        warning (frag, resId, null);
    }

    /**
     * Create a warning dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void warning (Fragment frag, int resId, ClickListener listener)
    {
        warning (frag, frag.getContext().getString(resId), listener);
    }

    /**
     * Create a warning dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     *
     */
    public static void warning (Fragment frag, String message)
    {
        warning (frag, message, null);
    }

    /**
     * Create a warning dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void warning (Fragment frag, String message, ClickListener listener)
    {
        DialogFrag df = create (R.style.WarnStyle, message, listener, false);
        df.show(frag.getFragmentManager(), message);
    }

    /**
     * Create a error dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     *
     */
    public static void error (Fragment frag, int resId)
    {
        error (frag, resId, null);
    }

    /**
     * Create a error dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void error (Fragment frag, int resId, ClickListener listener)
    {
        error (frag, frag.getContext().getString(resId), listener);
    }

    /**
     * Create a error dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     *
     */
    public static void error (Fragment frag, String message)
    {
        error (frag, message, null);
    }

    /**
     * Create a error dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void error (Fragment frag, String message, ClickListener listener)
    {
        DialogFrag df = create (R.style.ErrorStyle, message, listener, false);
        df.show(frag.getFragmentManager(), message);
    }

    /**
     * Create a question dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     *
     */
    public static void question (Fragment frag, int resId)
    {
        question (frag, resId, null);
    }

    /**
     * Create a question dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void question (Fragment frag, int resId, ClickListener listener)
    {
        question (frag, frag.getContext().getString(resId), listener);
    }

    /**
     * Create a question dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     *
     */
    public static void question (Fragment frag, String message)
    {
        question (frag, message, null);
    }

    /**
     * Create a question dialog with this message and a text entry field.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void question (Fragment frag, String message, ClickListener listener)
    {
        DialogFrag df = create (R.style.QuestionStyle, message, listener, true);
        df.show(frag.getFragmentManager(), message);
    }

    /**
     * Create a yesNo dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     *
     */
    public static void yesNo (Fragment frag, int resId)
    {
        yesNo (frag, resId, null);
    }

    /**
     * Create a yesNo dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag    - The fragment to associate this dialog with.
     * @param resId   - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void yesNo (Fragment frag, int resId, ClickListener listener)
    {
        yesNo (frag, frag.getContext().getString(resId), listener);
    }

    /**
     * Create a yesNo dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     *
     */
    public static void yesNo (Fragment frag, String message)
    {
        yesNo (frag, message, null);
    }

    /**
     * Create a yesNo dialog with this message.
     *
     * Button(S): ok
     *
     * @param frag     - The fragment to associate this dialog with.
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     */
    public static void yesNo (Fragment frag, String message, ClickListener listener)
    {
        DialogFrag df = create (R.style.YesNoStyle, message, listener, false);
        df.show(frag.getFragmentManager(), message);
    }

    /**
     * Create a new dialog fragment with the indicated style.
     *
     * @param style   - What style to use.
     * @param message - What message to display.
     *
     * @return the dialog that was created.
     */
    public static DialogFrag create (int style, String message, ClickListener listener, boolean showInput)
    {
        DialogFrag frag = new DialogFrag();
        Bundle args = new Bundle();
        args.putInt(DIALOG_STYLE_ID, style);
        args.putString(DIALOG_MSG, message);
        args.putBoolean(DIALOG_SHOW_INPUT, showInput);
        frag.setArguments(args);

        frag.listener = listener;



        return frag;
    }

    /**
     * Create the dialog. Do not call this method directly!
     *
     * @param savedInstanceState The saved instance
     *
     * @return the dialog that was created.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        // Got style?
        int styleId = R.style.ContactsStyle;
        String msg = "";
        boolean showInput = false;
        Bundle args = getArguments();
        if (null != args) {
            styleId     = args.getInt(DIALOG_STYLE_ID);
            msg         = args.getString(DIALOG_MSG);
            showInput   = args.getBoolean(DIALOG_SHOW_INPUT);
        }

        // Got dialog?
        dialog = new Dialog(getActivity(), styleId);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_frag);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // Set the message.

        TextView tv  = (TextView) dialog.findViewById(R.id.dialog_message);
        if (null != tv) {
            tv.setText(msg);
        }

        // Show the edit text field?
        if (showInput) {
            EditText dlgInput = (EditText)dialog.findViewById(R.id.dialog_input);
            if (null != dlgInput) {
                dlgInput.setVisibility(View.VISIBLE);
            }
        }

        // Add neg button listener?
        final Button btnNeg = (Button) dialog.findViewById(R.id.btn_dialog_neg);
        if (null != btnNeg) {
            String text = btnNeg.getText().toString();
            if (!text.isEmpty()) {
                btnNeg.setVisibility(View.VISIBLE);
                btnNeg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked (NEG_CLICK);
                        dialog.dismiss();
                    }
                });
            }
        }

        // Add neu button listener?
        final Button btnNeu = (Button) dialog.findViewById(R.id.btn_dialog_neu);
        if (null != btnNeu) {
            String text = btnNeu.getText().toString();
            if (!text.isEmpty()) {
                btnNeu.setVisibility(View.VISIBLE);
                btnNeu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked (NEU_CLICK);
                        dialog.dismiss();
                    }
                });
            }
        }

        // Add pos button listener?
        final Button btnPos = (Button) dialog.findViewById(R.id.btn_dialog_pos);
        if (null != btnPos) {
            String text = btnPos.getText().toString();
            if (!text.isEmpty()) {
                btnPos.setVisibility(View.VISIBLE);
                btnPos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked (POS_CLICK);
                        dialog.dismiss();
                    }
                });
            }
        }

        return dialog;
    }

    /**
     * Helper method calledr when button is pressed.
     * Will notify caller if listener is not null.
     *
     * @param btnId the button id that was clicked
     */
    protected void clicked (int btnId)
    {
        if (null != listener && null != dialog) {
            String text = "";
            @SuppressWarnings("ConstantConditions")
            TextView tv = (TextView) dialog.findViewById (R.id.dialog_input);
            if (null != tv) {
                text = (String)tv.getText();
            }

            listener.clicked (btnId, text);
        }
    }

}