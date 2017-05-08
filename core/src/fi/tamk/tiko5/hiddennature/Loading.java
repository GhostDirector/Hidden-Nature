package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by ghost on 8.5.2017.
 */

public class Loading implements Screen {

    private HiddenNature hn;
    private Batch batch;
    private Stage loadingStage;
    private Texture background, logo;
    private BitmapFont font;

    public Loading(HiddenNature hiddenNature){

        hn = hiddenNature;
        batch = hn.getBatch();
        loadingStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        background = new Texture(Gdx.files.internal("menu/backgroundLoading.jpg"));
        logo = new Texture("logos/trashPandaTrans.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Calibri1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.WHITE;
        param.borderWidth = 2;
        font = generator.generateFont(param);

    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        loadingStage.getBatch().begin();
        loadingStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());
        loadingStage.getBatch().draw(logo, 270, 180, logo.getWidth() * 0.19f,  logo.getHeight() * 0.19f);

        if (hn.getAm().update()){
            hn.createScreens();
            hn.mainMenu.selectScreen();
        }
        else {
            font.draw(batch, "Loading Assets", 280, 190);
            font.draw(batch, "Progress: " + (int)(hn.getAm().getProgress() * 100) + "%", 290, 160);
        }
        loadingStage.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
