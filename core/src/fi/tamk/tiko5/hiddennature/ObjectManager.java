package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class ObjectManager {
    private int id;
    private PrefHandler prefs;
    private boolean isLoad;
    private Array<Entity> entities = new Array<Entity>();
    private Array<Entity> silhouettes = new Array<Entity>();
    private Array<Entity> originals = new Array<Entity>();
    private HiddenNature hn;

    public ObjectManager(Level level, HiddenNature hiddenNature){
        hn = hiddenNature;
        prefs = new PrefHandler(level, hn);
        isLoad = prefs.loadLevel();
        id = level.getLevelID();
        
        if (!isLoad) {
            switch (this.id) {

                case 1:

                    entities.add(new Entity(hn.getAm().get("l1/HirviEnt.png", Texture.class), 282f, 78f, -1, 1f, false)); //1
                    entities.add(new Entity(hn.getAm().get("l1/JanisEnt.png", Texture.class), 281f, 30f, -2, 1f, false)); //2
                    entities.add(new Entity(hn.getAm().get("l1/KaapaEnt.png", Texture.class), 18f, 70f, -3, 0.7f, false)); //3
                    entities.add(new Entity(hn.getAm().get("l1/KiipijaEntOrig.png", Texture.class), 115f, 435f, -4, 1f, false)); //4
                    entities.add(new Entity(hn.getAm().get("l1/LehdokkiEntOrig.png", Texture.class), 487f, 5f, -5, 1f, false)); //5
                    entities.add(new Entity(hn.getAm().get("l1/LiitoOravaEnt.png", Texture.class), 457f, 400f, -6, 1f, false)); //6
                    entities.add(new Entity(hn.getAm().get("l1/OravaEnt.png", Texture.class), 90f, 360f, -7, 1f, false)); //7
                    entities.add(new Entity(hn.getAm().get("l1/PerhonenEntOrig.png", Texture.class), 726f, 17f, -8, 1f, false)); //8
                    entities.add(new Entity(hn.getAm().get("l1/TikkaEntOrig.png", Texture.class), 726f, 323f, -9, 1f, false)); //9
                    entities.add(new Entity(hn.getAm().get("l1/TinttiEnt.png", Texture.class), 190f, 398f, -10, 1f, false)); //10

                    originals.add(new Entity(hn.getAm().get("l1/HirviOrig.png", Texture.class), 0, 0, -1, 0.60f, false)); //1
                    originals.add(new Entity(hn.getAm().get("l1/JanisOrig.png", Texture.class), 0, 0, -2, 0.4f, false)); //2
                    originals.add(new Entity(hn.getAm().get("l1/KaapaOrig.png", Texture.class), 0, 0, -3, 1.14f, false)); //3
                    originals.add(new Entity(hn.getAm().get("l1/KiipijaEntOrig.png", Texture.class), 0, 0, -4, 1.45f, false)); //4
                    originals.add(new Entity(hn.getAm().get("l1/LehdokkiEntOrig.png", Texture.class), 0, 0, -5, 1.3f, false)); //5
                    originals.add(new Entity(hn.getAm().get("l1/LiitoOravaOrig.png", Texture.class), 0, 0, -6, 1.45f, false)); //6
                    originals.add(new Entity(hn.getAm().get("l1/OravaOrig.png", Texture.class), 0, 0, -7, 1.2f, false)); //7
                    originals.add(new Entity(hn.getAm().get("l1/PerhonenEntOrig.png", Texture.class), 0, 0, -8, 1.45f, false)); //8
                    originals.add(new Entity(hn.getAm().get("l1/TikkaEntOrig.png", Texture.class), 0, 0, -9, 1.45f, false)); //9
                    originals.add(new Entity(hn.getAm().get("l1/TinttiOrig.png", Texture.class), 0, 0, -10, 1.45f, false)); //10

                    silhouettes.add(new Entity(hn.getAm().get("l1/HirviSil.png", Texture.class), 70f, 300f, -1, 0.60f, false)); //1
                    silhouettes.add(new Entity(hn.getAm().get("l1/JanisSil.png", Texture.class), 230f, 310f, -2, 0.4f, false)); //2
                    silhouettes.add(new Entity(hn.getAm().get("l1/KaapaSil.png", Texture.class), 340f, 310f, -3, 1.14f, false)); //3
                    silhouettes.add(new Entity(hn.getAm().get("l1/KiipijaSil.png", Texture.class), 460f, 310f, -4, 1.45f, false)); //4
                    silhouettes.add(new Entity(hn.getAm().get("l1/LehdokkiSil.png", Texture.class), 550f, 310f, -5, 1.3f, false)); //5
                    silhouettes.add(new Entity(hn.getAm().get("l1/LiitoOravaSil.png", Texture.class), 70f, 120f, -6, 1.45f, false)); //6
                    silhouettes.add(new Entity(hn.getAm().get("l1/OravaSil.png", Texture.class), 190f, 120f, -7, 1.2f, false)); //7
                    silhouettes.add(new Entity(hn.getAm().get("l1/PerhonenSil.png", Texture.class), 340f, 120f, -8, 1.45f, false)); //8
                    silhouettes.add(new Entity(hn.getAm().get("l1/TikkaSil.png", Texture.class), 460f, 120f, -9, 1.45f, false)); //9
                    silhouettes.add(new Entity(hn.getAm().get("l1/TinttiSil.png", Texture.class), 550f, 120f, -10, 1.45f, false)); //10
                    break;

                case 2:
                    entities.add(new Entity(hn.getAm().get("l2/HiiriEnt.png", Texture.class), 533f, 75f, -1, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/JanisEnt.png", Texture.class), 85f, 20f, -2, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/KorpiEnt.png", Texture.class), 297f, 200f, -3, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/LumikkoEnt.png", Texture.class), 670f, 40f, -4, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/PolloEnt.png", Texture.class), 123f, 283f, -5, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/PyyEnt.png", Texture.class), 543f, 420f, -6, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/KettuEnt.png", Texture.class), 302f, 50f, -7, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/NaataEnt.png", Texture.class), 667f, 333f, -8, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/TiainenEnt.png", Texture.class), 20f, 360f, -9, 1f, false));
                    entities.add(new Entity(hn.getAm().get("l2/TulkkuEnt.png", Texture.class), 677f, 245f, -10, 1f, false));

                    originals.add(new Entity(hn.getAm().get("l2/HiiriOrig.png", Texture.class), 0, 0, -1, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/JanisOrig.png", Texture.class), 0, 0, -2, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/KorppiOrig.png", Texture.class), 0, 0, -3, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/LumikkoOrig.png", Texture.class), 0, 0, -4, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/PolloOrig.png", Texture.class), 0, 0, -5, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/PyyOrig.png", Texture.class), 0, 0, -6, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/KettuOrig.png", Texture.class), 0, 0, -7, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/NaataOrig.png", Texture.class), 0, 0, -8, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/TiainenOrig.png", Texture.class), 0, 0, -9, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l2/TulkkuOrig.png", Texture.class), 0, 0, -10, 0.4f, false));

                    silhouettes.add(new Entity(hn.getAm().get("l2/HiiriSil.png", Texture.class), 70f, 310f, -1, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/JanisSil.png", Texture.class), 200f, 310f, -2, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/KorppiSil.png", Texture.class), 300f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/LumikkoSil.png", Texture.class), 430f, 310f, -4, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/PolloSil.png", Texture.class), 540f, 310f, -5, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/PyySil.png", Texture.class), 70f, 120f, -6, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/KettuSil.png", Texture.class), 190f, 120f, -7, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/NaataSil.png", Texture.class), 320f, 120f, -8, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/TiainenSil.png", Texture.class), 440f, 120f, -9, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l2/TulkkuSil.png", Texture.class), 550f, 120f, -10, 0.4f, false));
                    break;

                case 3:
                    entities.add(new Entity(hn.getAm().get("l3/myyraEnt.png", Texture.class), 355f, 12f, -1, 0.65f, false));
                    entities.add(new Entity(hn.getAm().get("l3/sorsaEnt.png", Texture.class), 592f, 15f, -2, 0.7f, false));
                    entities.add(new Entity(hn.getAm().get("l3/supikoiraEnt.png", Texture.class), 251f, 93f, -3, 0.7f, false));
                    entities.add(new Entity(hn.getAm().get("l3/talitinttiEnt.png", Texture.class), 440f, 427f, -4, 0.6f, false));
                    entities.add(new Entity(hn.getAm().get("l3/tikkaEnt.png", Texture.class), 63f, 320f, -5, 0.60f, false));
                    entities.add(new Entity(hn.getAm().get("l3/yokkonenEnt.png", Texture.class), 735f, 145f, -6, 0.55f, false));
                    entities.add(new Entity(hn.getAm().get("l3/haperoEnt.png", Texture.class), 100f, 110f, -7, 0.55f, false));
                    entities.add(new Entity(hn.getAm().get("l3/karpassieniEnt.png", Texture.class), 190f, 20f, -8, 0.55f, false));
                    entities.add(new Entity(hn.getAm().get("l3/tattiEnt.png", Texture.class), 58f, 78f, -9, 0.55f, false));
                    entities.add(new Entity(hn.getAm().get("l3/uikkuEnt.png", Texture.class), 645f, 120f, -10, 0.65f, false));

                    originals.add(new Entity(hn.getAm().get("l3/myyraOrig.png", Texture.class), 0, 0, -1, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l3/sorsaOrig.png", Texture.class), 0, 0, -2, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l3/supikoiraOrig.png", Texture.class), 0, 0, -3, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l3/talitinttiOrig.png", Texture.class), 0, 0, -4, 0.5f, false));
                    originals.add(new Entity(hn.getAm().get("l3/tikkaOrig.png", Texture.class), 0, 0, -5, 0.5f, false));
                    originals.add(new Entity(hn.getAm().get("l3/yokkonenOrig.png", Texture.class), 0, 0, -6, 0.3f, false));
                    originals.add(new Entity(hn.getAm().get("l3/haperoOrig.png", Texture.class), 0, 0, -7, 0.3f, false));
                    originals.add(new Entity(hn.getAm().get("l3/karpassieniOrig.png", Texture.class), 0, 0, -8, 0.3f, false));
                    originals.add(new Entity(hn.getAm().get("l3/tattiOrig.png", Texture.class), 0, 0, -9, 0.3f, false));
                    originals.add(new Entity(hn.getAm().get("l3/uikkuOrig.png", Texture.class), 0, 0, -10, 0.6f, false));

                    silhouettes.add(new Entity(hn.getAm().get("l3/myyraSil.png", Texture.class), 70f, 310f, -1, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/sorsaSil.png", Texture.class), 190f, 310f, -2, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/supikoiraSil.png", Texture.class), 340f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/talitinttiSil.png", Texture.class), 450f, 310f, -4, 0.5f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/tikkaSil.png", Texture.class), 580f, 310f, -5, 0.5f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/yokkonenSil.png", Texture.class), 70f, 120f, -6, 0.3f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/haperoSil.png", Texture.class), 190f, 120f, -7, 0.3f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/karpassieniSil.png", Texture.class), 330f, 120f, -8, 0.3f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/tattiSil.png", Texture.class), 460f, 120f, -9, 0.3f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l3/uikkuSil.png", Texture.class), 560f, 120f, -10, 0.6f, false));
                    break;

                case 4:
                    entities.add(new Entity(hn.getAm().get("l4/haikaraEnt.png", Texture.class), 350f, 300f, -1, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/harmaalokkiEnt.png", Texture.class), 500f, 330f, -2, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/haukiEnt.png", Texture.class), 530f, 50f, -3, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/kerttunenEnt.png", Texture.class), 100f, 220f, -4, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/korentoEnt.png", Texture.class), 270f, 240f, -5, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/lokkiEnt.png", Texture.class), 400f, 430f, -6, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/myyraEnt.png", Texture.class), 265f, 90f, -7, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/sorsaEnt.png", Texture.class), 500f, 200f, -8, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/vastarakkiEnt.png", Texture.class), 190f, 40f, -9, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l4/sammakkoEnt.png", Texture.class), 265f, 180f, -10, 0.75f, false));

                    originals.add(new Entity(hn.getAm().get("l4/haikaraOrig.png", Texture.class), 0, 0, -1, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l4/harmaalokkiOrig.png", Texture.class), 0, 0, -2, 0.3f, false));
                    originals.add(new Entity(hn.getAm().get("l4/haukiOrig.png", Texture.class), 0, 0, -3, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l4/kerttunenOrig.png", Texture.class), 0, 0, -4, 0.15f, false));
                    originals.add(new Entity(hn.getAm().get("l4/korentoOrig.png", Texture.class), 0, 0, -5, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l4/lokkiOrig.png", Texture.class), 0, 0, -6, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l4/myyraOrig.png", Texture.class), 0, 0, -7, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l4/sorsaOrig.png", Texture.class), 0, 0, -8, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l4/vastarakkiOrig.png", Texture.class), 0, 0, -9, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l4/sammakkoOrig.png", Texture.class), 0, 0, -10, 0.2f, false));

                    silhouettes.add(new Entity(hn.getAm().get("l4/haikaraSil.png", Texture.class), 80, 270f, -1, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/harmaalokkiSil.png", Texture.class), 180f, 310f, -2, 0.3f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/haukiSil.png", Texture.class), 320f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/kerttunenSil.png", Texture.class), 460f, 310f, -4, 0.15f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/korentoSil.png", Texture.class), 560f, 310f, -5, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/lokkiSil.png", Texture.class), 80f, 130f, -6, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/myyraSil.png", Texture.class), 200f, 130f, -7, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/sorsaSil.png", Texture.class), 320f, 130f, -8, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/vastarakkiSil.png", Texture.class), 470f, 130f, -9, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l4/sammakkoSil.png", Texture.class), 560f, 130f, -10, 0.2f, false));
                    break;

                case 5:
                    entities.add(new Entity(hn.getAm().get("l5/hiiriEntYo.png", Texture.class), 350f, 300f, -1, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/huuhkajaEnt.png", Texture.class), 500f, 330f, -2, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/karhuEnt.png", Texture.class), 530f, 50f, -3, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/konnaEnt.png", Texture.class), 100f, 220f, -4, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/lepakkoEnt.png", Texture.class), 270f, 240f, -5, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/mayraEnt.png", Texture.class), 400f, 430f, -6, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/minkkiEnt.png", Texture.class), 265f, 90f, -7, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/naataEnt.png", Texture.class), 500f, 200f, -8, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/siiliEnt.png", Texture.class), 190f, 40f, -9, 0.75f, false));
                    entities.add(new Entity(hn.getAm().get("l5/yokkonenEnt.png", Texture.class), 265f, 180f, -10, 0.75f, false));

                    originals.add(new Entity(hn.getAm().get("l5/hiiriOrig.png", Texture.class), 0, 0, -1, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l5/HuuhkajaOrig.png", Texture.class), 0, 0, -2, 0.3f, false));
                    originals.add(new Entity(hn.getAm().get("l5/karhuOrig.png", Texture.class), 0, 0, -3, 0.4f, false));
                    originals.add(new Entity(hn.getAm().get("l5/konnaOrig.png", Texture.class), 0, 0, -4, 0.15f, false));
                    originals.add(new Entity(hn.getAm().get("l5/LepakkoOrig.png", Texture.class), 0, 0, -5, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l5/mayraOrig.png", Texture.class), 0, 0, -6, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l5/minkkiOrig.png", Texture.class), 0, 0, -7, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l5/naataOrig.png", Texture.class), 0, 0, -8, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l5/siiliOrig.png", Texture.class), 0, 0, -9, 0.2f, false));
                    originals.add(new Entity(hn.getAm().get("l5/yokkonenOrig.png", Texture.class), 0, 0, -10, 0.2f, false));

                    silhouettes.add(new Entity(hn.getAm().get("l5/hiiriSil.png", Texture.class), 80, 270f, -1, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/huuhkajaSil.png", Texture.class), 180f, 310f, -2, 0.3f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/karhuSil.png", Texture.class), 320f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/konnaSil.png", Texture.class), 460f, 310f, -4, 0.15f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/LepakkoSil.png", Texture.class), 560f, 310f, -5, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/mayraSil.png", Texture.class), 80f, 130f, -6, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/minkkiSil.png", Texture.class), 200f, 130f, -7, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/naataSil.png", Texture.class), 320f, 130f, -8, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/siiliSil.png", Texture.class), 470f, 130f, -9, 0.2f, false));
                    silhouettes.add(new Entity(hn.getAm().get("l5/yokkonenSil.png", Texture.class), 560f, 130f, -10, 0.2f, false));
                    break;
            }
        } else {
            prefs.loadLevel();
            
            entities = prefs.getEntities();
            originals = prefs.getOriginals();
            silhouettes = prefs.getSilhouettes();
        }
    }

    public Array<Entity> getOriginals() {
        return originals;
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public Array<Entity> getSilhouettes() {
        return silhouettes;
    }

}
