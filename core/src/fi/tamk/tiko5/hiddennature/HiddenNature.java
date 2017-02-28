package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

public class HiddenNature extends Game {
    private SpriteBatch batch;
    final float WORLD_WIDTH = 800f;
    final float WORLD_HEIGHT = 480f;
    //private Locale defaultLocale;
    //private I18NBundle myBundle;

    public SpriteBatch getBatch() {
        return batch;
    }
    //public I18NBundle getMyBundle() {
    //    return myBundle;
    //}

    public float getWORLD_WIDTH(){
        return WORLD_WIDTH;
    }

    public float getWORLD_HEIGHT(){
        return WORLD_HEIGHT;
    }

	@Override
	public void create () {
		batch = new SpriteBatch();
//        defaultLocale = Locale.getDefault();
//        myBundle = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), defaultLocale);
        setScreen(new MainMenu(this));
	}

    @Override
    public void dispose () {
        batch.dispose();
    }

	@Override
	public void render () {
		super.render();
	}

    public BitmapFont createFont(String file, int fontSize) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal(file));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;

        return generator.generateFont(parameter);
    }

}
