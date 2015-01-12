package com.android.ZooCeylon;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Chinthana on 18/12/2014.
 */
public class CustomFontsLoader {

    public static final int ArsenalBoldItalic =   0;
    public static final int ArsenalRegular =   1;
    public static final int BellotaLight =   2;

    private static final int NUM_OF_CUSTOM_FONTS = 3;

    private static boolean fontsLoaded = false;

    private static Typeface[] fonts = new Typeface[3];

    private static String[] fontPath = {
            "fonts/Arsenal-BoldItalic.otf",
            "fonts/Arsenal-Regular.otf",
            "fonts/Bellota-Light.otf"
    };


    /**
     * Returns a loaded custom font based on it's identifier.
     *
     * @param context - the current context
     * @param fontIdentifier = the identifier of the requested font
     *
     * @return Typeface object of the requested font.
     */
    public static Typeface getTypeface(Context context, int fontIdentifier) {
        if (!fontsLoaded) {
            loadFonts(context);
        }
        return fonts[fontIdentifier];
    }


    private static void loadFonts(Context context) {
        for (int i = 0; i < NUM_OF_CUSTOM_FONTS; i++) {
            fonts[i] = Typeface.createFromAsset(context.getAssets(), fontPath[i]);
        }
        fontsLoaded = true;

    }
}
