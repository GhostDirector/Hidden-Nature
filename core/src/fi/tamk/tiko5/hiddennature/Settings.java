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

/**
 * The Settings screen, provides buttons for language changing, game progress reset and mute.
 */
public class Settings implements Screen {
    private HiddenNature hn;
    private Texture background;
    private Stage settingStage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Preferences globalPrefs;
    private Entity quitButton, finButton, engButton, soundButton, resetButton;

    /**
     * Instantiates a new Settings.
     *
     * @param hiddenNature main. Contains asset manager.
     */
    public Settings(HiddenNature hiddenNature){
        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("menu/background.jpg"));
        batch = hn.getBatch();
        globalPrefs = Gdx.app.getPreferences("settings");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Calibri1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.GOLDENROD;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        selectScreen();
    }
    
    /**
     * Select this screen, reset stage, set actors and listeners.
     */
    public void selectScreen() {
        if (settingStage != null) {
            settingStage.dispose();
        }
    
        if(hn.isSound()){
            soundButton = new Entity(hn.getAm().get(hn.getLocalization().get("soundButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("soundpressedButton"), Texture.class), 20f, 200f, 1, true, 0.80f);
        }
        else{
            soundButton = new Entity(hn.getAm().get(hn.getLocalization().get("soundOffButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("soundOffpressedButton"), Texture.class), 20f, 200f, 1, true, 0.80f);
        }
    
        engButton = new Entity(hn.getAm().get(hn.getLocalization().get("engButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("engButton"), Texture.class), 200f, 300f, 2, true, 0.80f);
        finButton = new Entity(hn.getAm().get(hn.getLocalization().get("finButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("finButton"), Texture.class), 20f, 300f, 3, true, 0.80f);
        quitButton = new Entity(hn.getAm().get("menu/X.png", Texture.class), hn.getAm().get("menu/xPushedButton.png", Texture.class), 690f, 390f, 5, true, 0.25f);
        resetButton = new Entity(hn.getAm().get(hn.getLocalization().get("resetButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("resetpressedButton"), Texture.class), 20f, 100f, 4, true, 0.80f);
        
        settingStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        
        settingStage.addActor(quitButton);
        settingStage.addActor(finButton);
        settingStage.addActor(engButton);
        settingStage.addActor(soundButton);
        settingStage.addActor(resetButton);
        
        Gdx.input.setInputProcessor(settingStage);
        hn.setScreen(this);
    }

    /**
     * Listens entities by id for actions
     *
     * @param entity the entity that was clicked.
     */
    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("Settings", "no actions");
                entity.resetAction();
                break;

            case 1: Gdx.app.log("Settings", "sound");
                hn.playMenuMusic();
                entity.resetAction();
                selectScreen();
                break;

            case 2: Gdx.app.log("Settings", "Eng");
                globalPrefs.putInteger("localization", 2);
                globalPrefs.flush();
                hn.setLocalization(hn.getEng());
                hn.changeLang();
                selectScreen();
                entity.resetAction();
                break;

            case 3: Gdx.app.log("Settings", "Fin");
                globalPrefs.putInteger("localization", 3);
                globalPrefs.flush();
                hn.setLocalization(hn.getFin());
                hn.changeLang();
                selectScreen();
                entity.resetAction();
                break;

            case 4: Gdx.app.log("Settings", "reset");
                for (int i = 1; i <= hn.levelSelect.LEVELCOUNT; i++) {
                    globalPrefs.putBoolean("Reset"+i, true);
                }
                globalPrefs.putBoolean("Tutorial", true);
                globalPrefs.flush();
                entity.resetAction();
                break;

            case 5: Gdx.app.log("Settings", "cancel");
                hn.mainMenu.selectScreen();
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
        hn.music.pause();
    }

    @Override
    public void resume() {
        if (hn.globalPrefs.getInteger("sound", 1) == 1){
            hn.music.play();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
