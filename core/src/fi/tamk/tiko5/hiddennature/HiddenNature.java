package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Locale;

/**
 * Hidden nature, acts as a host for game session.
 */
public class HiddenNature extends Game {
    private SpriteBatch batch;
    private final float WORLD_WIDTH = 800f;
    private final float WORLD_HEIGHT = 480f;
    private Locale defaultLocale, fin, eng;
    private I18NBundle Localization;
    private boolean sound;
    /**
     * The Global prefs.
     *
     * Provides information about sound, reset and localization for classes.
     */
    public Preferences globalPrefs;
    /**
     * The Game screen.
     */
    GameScreen gameScreen;
    /**
     * The Pause menu.
     */
    PauseMenu pauseMenu;
    /**
     * The Level select.
     */
    LevelSelect levelSelect;
    /**
     * The Main menu.
     */
    MainMenu mainMenu;
    /**
     * The Credits.
     */
    Credits credits;
    /**
     * The Settings.
     */
    Settings settings;
    /**
     * The Music.
     *
     * Menu music for credits, settings, level select and main menu.
     */
    protected Music music;
    /**
     * The Game music.
     *
     * Music for game screen and pause menu.
     */
    protected Music gameMusic;
    private static AssetManager am;
    private Loading loading;
    private OrthographicCamera camera;

    @Override
    public void dispose () {
        batch.dispose();
    }

	@Override
	public void create () {

        am = new AssetManager();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Menumusiikki.mp3"));
        music.setVolume(0.7f);
        music.setLooping(true);
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/Tasomusiikki.mp3"));
        gameMusic.setVolume(0.7f);
        gameMusic.setLooping(true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);

        loading = new Loading(this);
        globalPrefs = Gdx.app.getPreferences("settings");
        defaultLocale = defaultLocale.getDefault();
        fin = new Locale("fi", "FI");
        eng = new Locale("en", "GB");

        int lang = globalPrefs.getInteger("localization", 1);

        switch (lang){
            case 1: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), defaultLocale);
                break;
            case 2: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), eng);
                break;
            case 3: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), fin);
                break;
        }
        
        sound = true;

        loadAssets();

        setScreen(loading);

        //mainMenu.selectScreen();

    }

    /**
     * Loads assets for game using AssetManager.
     *
     */
    public void loadAssets(){

        //Universal Assets
        am.load("menu/background.jpg", Texture.class);
        am.load("menu/X.png", Texture.class);
        am.load("menu/xPushedButton.png", Texture.class);

        //pausemenu/gamescreen Assets

        am.load("menu/PauseMenuFancy.jpg", Texture.class);
        am.load("menu/HelpButton.png", Texture.class);
        am.load("menu/HelpPushedButton.png", Texture.class);
        am.load(Localization.get("returnToMenu"), Texture.class);
        am.load(Localization.get("returnToMenuPressed"), Texture.class);
        am.load(Localization.get("pauseTutBox"), Texture.class);
        am.load("menu/PauseMenu.png", Texture.class);
        am.load("menu/PauseMenuPushedButton.png", Texture.class);
        am.load("menu/tutorialPopupIsompi.png", Texture.class);
        am.load("menu/LevelPassPopup.png", Texture.class);
        am.load("menu/ohjePopupIsompi.png", Texture.class);
        am.load("menu/ArrowBackButton.png", Texture.class);
        am.load("menu/ArrowBackPushedButton.png", Texture.class);
        am.load("menu/FinalPopup.png", Texture.class);
        am.load("menu/LoppuPopup.png", Texture.class);
        am.load("menu/TasoLapaistyPopup.png", Texture.class);

        //Mainmenu Assets
        am.load("menu/SettingsButton.png", Texture.class);
        am.load("menu/SettingsPushedButton.png", Texture.class);
        am.load("menu/title.png", Texture.class);
        am.load(Localization.get("playButton"), Texture.class);
        am.load(Localization.get("playpressedButton"), Texture.class);
        am.load(Localization.get("creditsButton"), Texture.class);
        am.load(Localization.get("creditspressedButton"), Texture.class);
        am.load(Localization.get("quitButton"), Texture.class);
        am.load(Localization.get("quitpressedButton"), Texture.class);

        //Settings Assets
        am.load(Localization.get("soundButton"), Texture.class);
        am.load(Localization.get("soundpressedButton"), Texture.class);
        am.load(Localization.get("soundOffButton"), Texture.class);
        am.load(Localization.get("soundOffpressedButton"), Texture.class);
        am.load(Localization.get("engButton"), Texture.class);
        am.load(Localization.get("finButton"), Texture.class);
        am.load(Localization.get("resetButton"), Texture.class);
        am.load(Localization.get("resetpressedButton"), Texture.class);

        //Credits Assets
        am.load("logos/Lumu.png", Texture.class);
        am.load(Localization.get("trashLogo"), Texture.class);
        am.load(Localization.get("tamkLogo"), Texture.class);
        am.load(Localization.get("tikoLogo"), Texture.class);
        am.load(Localization.get("vapriikkiLogo"), Texture.class);

        //level select Assets
        am.load("menu/NuoliVasen.png", Texture.class);
        am.load("menu/NuoliVasenPushedButton.png", Texture.class);
        am.load("menu/NuoliOikea.png", Texture.class);
        am.load("menu/NuoliOikeaPushedButton.png", Texture.class);
        am.load("menu/roundBox.jpg", Texture.class);
        am.load("menu/V.png", Texture.class);
        am.load("menu/vPushedButton.png", Texture.class);

        //Level 1 Assets
        am.load("l1/Taso1.jpg", Texture.class);
        //l1 ent
        am.load("l1/HirviEnt.png", Texture.class);
        am.load("l1/JanisEnt.png", Texture.class);
        am.load("l1/KaapaEnt.png", Texture.class);
        am.load("l1/KiipijaEntOrig.png", Texture.class); //!
        am.load("l1/LehdokkiEntOrig.png", Texture.class); //!
        am.load("l1/LiitoOravaEnt.png", Texture.class);
        am.load("l1/OravaEnt.png", Texture.class);
        am.load("l1/PerhonenEntOrig.png", Texture.class); //!
        am.load("l1/TikkaEntOrig.png", Texture.class); //!
        am.load("l1/TinttiEnt.png", Texture.class);
        //l1 orig
        am.load("l1/HirviOrig.png", Texture.class);
        am.load("l1/JanisOrig.png", Texture.class);
        am.load("l1/KaapaOrig.png", Texture.class);
        am.load("l1/LiitoOravaOrig.png", Texture.class);
        am.load("l1/OravaOrig.png", Texture.class);
        am.load("l1/TinttiOrig.png", Texture.class);
        //l1 sil
        am.load("l1/HirviSil.png", Texture.class);
        am.load("l1/JanisSil.png", Texture.class);
        am.load("l1/KaapaSil.png", Texture.class);
        am.load("l1/KiipijaSil.png", Texture.class);
        am.load("l1/LehdokkiSil.png", Texture.class);
        am.load("l1/LiitoOravaSil.png", Texture.class);
        am.load("l1/OravaSil.png", Texture.class);
        am.load("l1/PerhonenSil.png", Texture.class);
        am.load("l1/TikkaSil.png", Texture.class);
        am.load("l1/TinttiSil.png", Texture.class);

        //Level 2 Assets
        am.load("l2/Taso2.jpg", Texture.class);
        //l2 ent
        am.load("l2/HiiriEnt.png", Texture.class);
        am.load("l2/JanisEnt.png", Texture.class);
        am.load("l2/KorpiEnt.png", Texture.class);
        am.load("l2/LumikkoEnt.png", Texture.class);
        am.load("l2/PolloEnt.png", Texture.class);
        am.load("l2/PyyEnt.png", Texture.class);
        am.load("l2/KettuEnt.png", Texture.class);
        am.load("l2/NaataEnt.png", Texture.class);
        am.load("l2/TiainenEnt.png", Texture.class);
        am.load("l2/TulkkuEnt.png", Texture.class);
        //l2 orig
        am.load("l2/HiiriOrig.png", Texture.class);
        am.load("l2/JanisOrig.png", Texture.class);
        am.load("l2/KorppiOrig.png", Texture.class);
        am.load("l2/LumikkoOrig.png", Texture.class);
        am.load("l2/PolloOrig.png", Texture.class);
        am.load("l2/PyyOrig.png", Texture.class);
        am.load("l2/KettuOrig.png", Texture.class);
        am.load("l2/NaataOrig.png", Texture.class);
        am.load("l2/TiainenOrig.png", Texture.class);
        am.load("l2/TulkkuOrig.png", Texture.class);
        //l2 sil
        am.load("l2/HiiriSil.png", Texture.class);
        am.load("l2/JanisSil.png", Texture.class);
        am.load("l2/KorppiSil.png", Texture.class);
        am.load("l2/LumikkoSil.png", Texture.class);
        am.load("l2/PolloSil.png", Texture.class);
        am.load("l2/PyySil.png", Texture.class);
        am.load("l2/KettuSil.png", Texture.class);
        am.load("l2/NaataSil.png", Texture.class);
        am.load("l2/TiainenSil.png", Texture.class);
        am.load("l2/TulkkuSil.png", Texture.class);

        //Level 3 Assets
        am.load("l3/Taso3.jpg", Texture.class);
        //l3 ent
        am.load("l3/myyraEnt.png", Texture.class);
        am.load("l3/sorsaEnt.png", Texture.class);
        am.load("l3/supikoiraEnt.png", Texture.class);
        am.load("l3/talitinttiEnt.png", Texture.class);
        am.load("l3/tikkaEnt.png", Texture.class);
        am.load("l3/yokkonenEnt.png", Texture.class);
        am.load("l3/haperoEnt.png", Texture.class);
        am.load("l3/karpassieniEnt.png", Texture.class);
        am.load("l3/tattiEnt.png", Texture.class);
        am.load("l3/uikkuEnt.png", Texture.class);
        //l3 orig
        am.load("l3/myyraOrig.png", Texture.class);
        am.load("l3/sorsaOrig.png", Texture.class);
        am.load("l3/supikoiraOrig.png", Texture.class);
        am.load("l3/talitinttiOrig.png", Texture.class);
        am.load("l3/tikkaOrig.png", Texture.class);
        am.load("l3/yokkonenOrig.png", Texture.class);
        am.load("l3/haperoOrig.png", Texture.class);
        am.load("l3/karpassieniOrig.png", Texture.class);
        am.load("l3/tattiOrig.png", Texture.class);
        am.load("l3/uikkuOrig.png", Texture.class);
        //l3 sil
        am.load("l3/myyraSil.png", Texture.class);
        am.load("l3/sorsaSil.png", Texture.class);
        am.load("l3/supikoiraSil.png", Texture.class);
        am.load("l3/talitinttiSil.png", Texture.class);
        am.load("l3/tikkaSil.png", Texture.class);
        am.load("l3/yokkonenSil.png", Texture.class);
        am.load("l3/haperoSil.png", Texture.class);
        am.load("l3/karpassieniSil.png", Texture.class);
        am.load("l3/tattiSil.png", Texture.class);
        am.load("l3/uikkuSil.png", Texture.class);

        //Level 4 Assets
        am.load("l4/Taso4.png", Texture.class);
        //l4 ent
        am.load("l4/haikaraEnt.png", Texture.class);
        am.load("l4/harmaalokkiEnt.png", Texture.class);
        am.load("l4/haukiEnt.png", Texture.class);
        am.load("l4/kerttunenEnt.png", Texture.class);
        am.load("l4/korentoEnt.png", Texture.class);
        am.load("l4/lokkiEnt.png", Texture.class);
        am.load("l4/myyraEnt.png", Texture.class);
        am.load("l4/sorsaEnt.png", Texture.class);
        am.load("l4/vastarakkiEnt.png", Texture.class);
        am.load("l4/sammakkoEnt.png", Texture.class);
        //l4 orig
        am.load("l4/haikaraOrig.png", Texture.class);
        am.load("l4/harmaalokkiOrig.png", Texture.class);
        am.load("l4/haukiOrig.png", Texture.class);
        am.load("l4/kerttunenOrig.png", Texture.class);
        am.load("l4/korentoOrig.png", Texture.class);
        am.load("l4/lokkiOrig.png", Texture.class);
        am.load("l4/myyraOrig.png", Texture.class);
        am.load("l4/sorsaOrig.png", Texture.class);
        am.load("l4/vastarakkiOrig.png", Texture.class);
        am.load("l4/sammakkoOrig.png", Texture.class);
        //l4 sil
        am.load("l4/haikaraSil.png", Texture.class);
        am.load("l4/harmaalokkiSil.png", Texture.class);
        am.load("l4/haukiSil.png", Texture.class);
        am.load("l4/kerttunenSil.png", Texture.class);
        am.load("l4/korentoSil.png", Texture.class);
        am.load("l4/lokkiSil.png", Texture.class);
        am.load("l4/myyraSil.png", Texture.class);
        am.load("l4/sorsaSil.png", Texture.class);
        am.load("l4/vastarakkiSil.png", Texture.class);
        am.load("l4/sammakkoSil.png", Texture.class);

        //Level 5 Assets
        am.load("l5/Taso5.jpg", Texture.class);
        //l5 ent
        am.load("l5/hiiriEntYo.png", Texture.class);
        am.load("l5/huuhkajaEnt.png", Texture.class);
        am.load("l5/karhuEnt.png", Texture.class);
        am.load("l5/konnaEnt.png", Texture.class);
        am.load("l5/lepakkoEnt.png", Texture.class);
        am.load("l5/mayraEnt.png", Texture.class);
        am.load("l5/minkkiEnt.png", Texture.class);
        am.load("l5/naataEnt.png", Texture.class);
        am.load("l5/siiliEnt.png", Texture.class);
        am.load("l5/yokkonenEnt.png", Texture.class);
        //l5 orig
        am.load("l5/hiiriOrig.png", Texture.class);
        am.load("l5/HuuhkajaOrig.png", Texture.class);
        am.load("l5/karhuOrig.png", Texture.class);
        am.load("l5/konnaOrig.png", Texture.class);
        am.load("l5/LepakkoOrig.png", Texture.class);
        am.load("l5/mayraOrig.png", Texture.class);
        am.load("l5/minkkiOrig.png", Texture.class);
        am.load("l5/naataOrig.png", Texture.class);
        am.load("l5/siiliOrig.png", Texture.class);
        am.load("l5/yokkonenOrig.png", Texture.class);
        //l5 sil
        am.load("l5/hiiriSil.png", Texture.class);
        am.load("l5/huuhkajaSil.png", Texture.class);
        am.load("l5/karhuSil.png", Texture.class);
        am.load("l5/konnaSil.png", Texture.class);
        am.load("l5/LepakkoSil.png", Texture.class);
        am.load("l5/mayraSil.png", Texture.class);
        am.load("l5/minkkiSil.png", Texture.class);
        am.load("l5/naataSil.png", Texture.class);
        am.load("l5/siiliSil.png", Texture.class);
        am.load("l5/yokkonenSil.png", Texture.class);

        //am.finishLoading();

        //createScreens();

    }

    /**
     * Creates instances for screens.
     */
    public void createScreens(){
        levelSelect = new LevelSelect(this);
        mainMenu = new MainMenu(this);
        credits = new Credits(this);
        settings = new Settings(this);
    }

    /**
     * Loads assets for game by localization using AssetManager.
     */
    public void changeLang(){

        am.load(Localization.get("trashLogo"), Texture.class);
        am.load(Localization.get("tamkLogo"), Texture.class);
        am.load(Localization.get("tikoLogo"), Texture.class);
        am.load(Localization.get("vapriikkiLogo"), Texture.class);

        am.load(Localization.get("soundButton"), Texture.class);
        am.load(Localization.get("soundpressedButton"), Texture.class);
        am.load(Localization.get("soundOffButton"), Texture.class);
        am.load(Localization.get("soundOffpressedButton"), Texture.class);
        am.load(Localization.get("engButton"), Texture.class);
        am.load(Localization.get("finButton"), Texture.class);
        am.load(Localization.get("resetButton"), Texture.class);
        am.load(Localization.get("resetpressedButton"), Texture.class);

        am.load(Localization.get("playButton"), Texture.class);
        am.load(Localization.get("playpressedButton"), Texture.class);
        am.load(Localization.get("creditsButton"), Texture.class);
        am.load(Localization.get("creditspressedButton"), Texture.class);
        am.load(Localization.get("quitButton"), Texture.class);
        am.load(Localization.get("quitpressedButton"), Texture.class);

        am.load(Localization.get("returnToMenu"), Texture.class);
        am.load(Localization.get("returnToMenuPressed"), Texture.class);
        am.load(Localization.get("pauseTutBox"), Texture.class);

        am.finishLoading();
    }

    /**
     * Get am asset manager.
     *
     * @return the asset manager
     */
    public AssetManager getAm(){
        return am;
    }

    /**
     * Play menu music, checks whether music is muted or not.
     */
    public void playMenuMusic(){
        if (globalPrefs.getInteger("sound", 1) == 1) {
            globalPrefs.putInteger("sound", 2);
            globalPrefs.flush();
            music.pause();
            sound = false;
        } else {
            globalPrefs.putInteger("sound", 1);
            globalPrefs.flush();
            music.play();
            sound = true;
        }
    }

    /**
     * Get finnish locale.
     *
     * @return the locale
     */
    public Locale getFin(){
        return fin;
    }

    /**
     * Get english locale.
     *
     * @return the locale
     */
    public Locale getEng(){
        return eng;
    }

    /**
     * Is sound boolean.
     *
     * @return the boolean
     */
    public boolean isSound(){
        return sound;
    }

    /**
     * Gets localization.
     *
     * @return the localization
     */
    public I18NBundle getLocalization() {
        return Localization;
    }

    /**
     * Sets localization.
     *
     * @param lang the lang
     */
    public void setLocalization(Locale lang) {
        Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), lang);
    }

	@Override
	public void render () {
		super.render();
        batch.setProjectionMatrix(camera.combined);
	}
    
    /**
     * Gets batch.
     *
     * @return the batch
     */
    public SpriteBatch getBatch() {
        return batch;
    }
    
    /**
     * Get world width float.
     *
     * @return the float
     */
    public float getWORLD_WIDTH(){
        return WORLD_WIDTH;
    }
    
    /**
     * Get world height float.
     *
     * @return the float
     */
    public float getWORLD_HEIGHT(){
        return WORLD_HEIGHT;
    }

}
