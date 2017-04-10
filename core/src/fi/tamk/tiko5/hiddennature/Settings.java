package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Settings implements Screen {
    private HiddenNature hn;
    private Texture background;
    private Stage settingStage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Preferences globalPrefs;
    private Entity quitButton, finButton, engButton, soundButton, resetButton;

    public Settings(HiddenNature hiddenNature){
        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("background.jpg"));
        batch = hn.getBatch();
        settingStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        globalPrefs = Gdx.app.getPreferences("settings");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.DARK_GRAY;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        if(hn.isSound() == true){
            soundButton = new Entity(hn.getLocalization().get("soundButton"), hn.getLocalization().get("soundpressedButton"), 20f, 200f, 1, true, 0.80f);
        }
        else{
            soundButton = new Entity(hn.getLocalization().get("soundOffButton"), hn.getLocalization().get("soundOffpressedButton"), 20f, 200f, 1, true, 0.80f);
        }

        engButton = new Entity(hn.getLocalization().get("engButton"), hn.getLocalization().get("engButton"), 200f, 300f, 2, true, 0.80f);
        finButton = new Entity(hn.getLocalization().get("finButton"), hn.getLocalization().get("finButton"), 20f, 300f, 3, true, 0.80f);


        quitButton = new Entity("X.png", "xPushedButton.png", 690f, 390f, 5, true, 0.25f);
        resetButton = new Entity(hn.getLocalization().get("resetButton"), hn.getLocalization().get("resetpressedButton"), 20f, 100f, 4, true, 0.80f);

        settingStage.addActor(quitButton);
        settingStage.addActor(finButton);
        settingStage.addActor(engButton);
        settingStage.addActor(soundButton);
        settingStage.addActor(resetButton);

        Gdx.input.setInputProcessor(settingStage);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("Settings", "no actions");
                entity.resetAction();
                break;

            case 1: Gdx.app.log("Settings", "");

                entity.resetAction();
                break;

            case 2: Gdx.app.log("Settings", "Eng");
                globalPrefs.putInteger("localization", 2);
                globalPrefs.flush();
                hn.setLocalization(hn.getEng());
                hn.setScreen(new Settings(hn));
                entity.resetAction();
                break;

            case 3: Gdx.app.log("Settings", "Fin");
                globalPrefs.putInteger("localization", 3);
                globalPrefs.flush();
                hn.setLocalization(hn.getFin());
                hn.setScreen(new Settings(hn));
                entity.resetAction();
                break;

            case 4: Gdx.app.log("Settings", "reset");
                globalPrefs.putBoolean("Reset1", true);
                globalPrefs.putBoolean("Reset2", true);
                globalPrefs.putBoolean("Reset3", true);
                globalPrefs.flush();
                entity.resetAction();
                break;

            case 5: Gdx.app.log("Settings", "cancel");
                hn.setScreen(new MainMenu(hn));
                entity.resetAction();
                break;
        }
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        settingStage.getBatch().begin();
        settingStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());
        settingStage.getBatch().end();

        settingStage.act(Gdx.graphics.getDeltaTime());

        settingStage.draw();

        getEntityID(quitButton);
        getEntityID(finButton);
        getEntityID(engButton);
        getEntityID(soundButton);
        getEntityID(resetButton);

    }
    
    @Override
    public void show() {
        
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
