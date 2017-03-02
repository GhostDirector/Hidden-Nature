package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Ghost on 2.3.2017.
 */

public class Credits implements Screen {

    private HiddenNature hn;
    private Texture background;
    private Stage creditsStage;
    private SpriteBatch batch;
    private Entity entity1;
    private BitmapFont font;
    private boolean gameIsOn;

    public Credits(HiddenNature hiddenNature){

        gameIsOn = true;

        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("background.jpeg"));

        batch = hn.getBatch();

        creditsStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        entity1 = new Entity("return.png", "returnpressed.png", 690f, 390f, 1, true);
        creditsStage.addActor(entity1);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.PINK;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        Gdx.input.setInputProcessor(creditsStage);

    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("entity", "no actions");
                entity.resetAction();
                break;

            case 1: hn.setScreen(new MainMenu(hn));
                entity.resetAction();
                break;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (gameIsOn){

            getEntityID(entity1);

            creditsStage.getBatch().begin();
            creditsStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
            font.draw(batch, "Credits:", 100, 450);
            font.draw(batch, "Project Lead: Kaisa", 100, 350);
            font.draw(batch, "Programming: Joni", 100, 300);
            font.draw(batch, "Programming: Jyri", 100, 250);
            font.draw(batch, "Graphics: Severi", 100, 200);


            creditsStage.getBatch().end();

            creditsStage.act(Gdx.graphics.getDeltaTime());

            creditsStage.draw();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        gameIsOn = false;
    }

    @Override
    public void resume() {
        gameIsOn = true;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
