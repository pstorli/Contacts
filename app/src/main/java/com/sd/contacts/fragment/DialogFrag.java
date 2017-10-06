package com.sd.contacts.fragment;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
    public static final String DIALOG_MSG       = "dialogMessage";
    public static final String DIALOG_STYLE_ID  = "dialogStyleId";

    /**
     * Who to call back when a button is clicked.
     */
    protected ClickListener listener = null;

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
     * @param message  - The message to display.
     *
     * @return the dialog that was created.
     */
    public static DialogFrag info (String message)
    {
        return create (R.style.InfoStyle, message, null);
    }

    /**
     * Create an info dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     * @return the dialog that was created.
     */
    public static DialogFrag info (String message, ClickListener listener)
    {
        return create (R.style.InfoStyle, message, listener);
    }

    /**
     * Create a warning dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     *
     * @return the dialog that was created.
     */
    public static DialogFrag warning (String message)
    {
        return create (R.style.WarnStyle, message, null);
    }

    /**
     * Create a warning dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     * @return the dialog that was created.
     */
    public static DialogFrag warning (String message, ClickListener listener)
    {
        return create (R.style.WarnStyle, message, listener);
    }

    /**
     * Create an error dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     *
     * @return the dialog that was created.
     */
    public static DialogFrag error (String message)
    {
        return create (R.style.ErrorStyle, message, null);
    }

    /**
     * Create an error dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     * @return the dialog that was created.
     */
    public static DialogFrag error (String message, ClickListener listener)
    {
        return create (R.style.ErrorStyle, message, listener);
    }

    /**
     * Create a question dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     *
     * @return the dialog that was created.
     */
    public static DialogFrag question (String message)
    {
        return create (R.style.QuestionStyle, message, null);
    }

    /**
     * Create a question dialog with this message.
     *
     * Button(S): ok
     *
     * @param message  - The message to display.
     * @param listener - Who you gonna call back?
     *
     * @return the dialog that was created.
     */
    public static DialogFrag question (String message, ClickListener listener)
    {
        return create (R.style.QuestionStyle, message, listener);
    }

    /**
     * Create a new dialog fragment with the indicated style.
     *
     * @param style   - What style to use.
     * @param message - What message to display.
     *
     * @return the dialog that was created.
     */
    public static DialogFrag create (int style, String message, ClickListener listener)
    {
        DialogFrag frag = new DialogFrag();
        Bundle args = new Bundle();
        args.putInt(DIALOG_STYLE_ID, style);
        args.putString(DIALOG_MSG, message);
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
        int styleId = getArguments().getInt(DIALOG_STYLE_ID);

        // Got dialog?
        final Dialog dialog = new Dialog(getActivity(), styleId);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_frag);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // Set the message.
        final String msg = getArguments().getString(DIALOG_MSG);
        TextView tv  = (TextView) dialog.findViewById(R.id.dialog_message);
        if (null != tv) {
            tv.setText(msg);
        }

        // Add neg button listener?
        final Button btnNeg = (Button) dialog.findViewById(R.id.btn_dialog_neg);
        if (null != btnNeg && View.VISIBLE == btnNeg.getVisibility()) {
            btnNeg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked (R.id.btn_dialog_neg);
                    dialog.dismiss();
                }
            });
        }

        // Add neu button listener?
        final Button btnNeu = (Button) dialog.findViewById(R.id.btn_dialog_neu);
        if (null != btnNeu && View.VISIBLE == btnNeu.getVisibility()) {
            btnNeu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked (R.id.btn_dialog_neu);
                    dialog.dismiss();
                }
            });
        }

        // Add pos button listener?
        final Button btnPos = (Button) dialog.findViewById(R.id.btn_dialog_pos);
        if (null != btnPos && View.VISIBLE == btnPos.getVisibility()) {
            btnPos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked (R.id.btn_dialog_pos);
                    dialog.dismiss();
                }
            });
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
        if (null != listener) {
            String text = "";
            @SuppressWarnings("ConstantConditions") TextView tv = (TextView) getView ().findViewById (R.id.dialog_message);
            if (null != tv) {
                text = (String)tv.getText();
            }

            listener.clicked (btnId, text);
        }
    }

}