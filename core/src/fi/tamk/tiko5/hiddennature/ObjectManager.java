package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.utils.Array;

public class ObjectManager {
    private int id;
    private PrefHandler prefs;
    private boolean isLoad;
    private Array<Entity> entities = new Array<Entity>();
    private Array<Entity> silhouettes = new Array<Entity>();
    private Array<Entity> originals = new Array<Entity>();

    public ObjectManager(Level level){
        prefs = new PrefHandler(level);
        isLoad = prefs.loadLevel();
        id = level.getLevelID();
        
        if (!isLoad) {
            switch (this.id) {

                case 1:
                    entities.add(new Entity("l1/HirviEnt.png", 282f, 78f, -1, 1f, false)); //1
                    entities.add(new Entity("l1/JanisEnt.png", 281f, 30f, -2, 1f, false)); //2
                    entities.add(new Entity("l1/KaapaEnt.png", 18f, 70f, -3, 0.7f, false)); //3
                    entities.add(new Entity("l1/KiipijaEntOrig.png", 115f, 435f, -4, 1f, false)); //4
                    entities.add(new Entity("l1/LehdokkiEntOrig.png", 487f, 5f, -5, 1f, false)); //5
                    entities.add(new Entity("l1/LiitoOravaEnt.png", 457f, 400f, -6, 1f, false)); //6
                    entities.add(new Entity("l1/OravaEnt.png", 90f, 360f, -7, 1f, false)); //7
                    entities.add(new Entity("l1/PerhonenEntOrig.png", 726f, 17f, -8, 1f, false)); //8
                    entities.add(new Entity("l1/TikkaEntOrig.png", 726f, 323f, -9, 1f, false)); //9
                    entities.add(new Entity("l1/TinttiEnt.png", 190f, 398f, -10, 1f, false)); //10

                    originals.add(new Entity("l1/HirviOrig.png", 0, 0, -1, 0.60f, false)); //1
                    originals.add(new Entity("l1/JanisOrig.png", 0, 0, -2, 0.4f, false)); //2
                    originals.add(new Entity("l1/KaapaOrig.png", 0, 0, -3, 1.14f, false)); //3
                    originals.add(new Entity("l1/KiipijaEntOrig.png", 0, 0, -4, 1.45f, false)); //4
                    originals.add(new Entity("l1/LehdokkiEntOrig.png", 0, 0, -5, 1.3f, false)); //5
                    originals.add(new Entity("l1/LiitoOravaOrig.png", 0, 0, -6, 1.45f, false)); //6
                    originals.add(new Entity("l1/OravaOrig.png", 0, 0, -7, 1.2f, false)); //7
                    originals.add(new Entity("l1/PerhonenEntOrig.png", 0, 0, -8, 1.45f, false)); //8
                    originals.add(new Entity("l1/TikkaEntOrig.png", 0, 0, -9, 1.45f, false)); //9
                    originals.add(new Entity("l1/TinttiOrig.png", 0, 0, -10, 1.45f, false)); //10

                    silhouettes.add(new Entity("l1/HirviSil.png", 70f, 300f, -1, 0.60f, false)); //1
                    silhouettes.add(new Entity("l1/JanisSil.png", 230f, 310f, -2, 0.4f, false)); //2
                    silhouettes.add(new Entity("l1/KaapaSil.png", 340f, 310f, -3, 1.14f, false)); //3
                    silhouettes.add(new Entity("l1/KiipijaSil.png", 460f, 310f, -4, 1.45f, false)); //4
                    silhouettes.add(new Entity("l1/LehdokkiSil.png", 550f, 310f, -5, 1.3f, false)); //5
                    silhouettes.add(new Entity("l1/LiitoOravaSil.png", 70f, 120f, -6, 1.45f, false)); //6
                    silhouettes.add(new Entity("l1/OravaSil.png", 190f, 120f, -7, 1.2f, false)); //7
                    silhouettes.add(new Entity("l1/PerhonenSil.png", 340f, 120f, -8, 1.45f, false)); //8
                    silhouettes.add(new Entity("l1/TikkaSil.png", 460f, 120f, -9, 1.45f, false)); //9
                    silhouettes.add(new Entity("l1/TinttiSil.png", 550f, 120f, -10, 1.45f, false)); //10
                    break;

                case 2:
                    entities.add(new Entity("l2/HiiriEnt.png", 533f, 75f, -1, 1f, false));
                    entities.add(new Entity("l2/JanisEnt.png", 85f, 20f, -2, 1f, false));
                    entities.add(new Entity("l2/KorpiEnt.png", 297f, 200f, -3, 1f, false));
                    entities.add(new Entity("l2/LumikkoEnt.png", 670f, 40f, -4, 1f, false));
                    entities.add(new Entity("l2/PolloEnt.png", 123f, 283f, -5, 1f, false));
                    entities.add(new Entity("l2/PyyEnt.png", 543f, 420f, -6, 1f, false));
                    entities.add(new Entity("l2/KettuEnt.png", 302f, 50f, -7, 1f, false));
                    entities.add(new Entity("l2/NaataEnt.png", 667f, 333f, -8, 1f, false));
                    entities.add(new Entity("l2/TiainenEnt.png", 20f, 360f, -9, 1f, false));
                    entities.add(new Entity("l2/TulkkuEnt.png", 677f, 245f, -10, 1f, false));

                    originals.add(new Entity("l2/HiiriOrig.png", 0, 0, -1, 0.4f, false));
                    originals.add(new Entity("l2/JanisOrig.png", 0, 0, -2, 0.4f, false));
                    originals.add(new Entity("l2/KorppiOrig.png", 0, 0, -3, 0.4f, false));
                    originals.add(new Entity("l2/LumikkoOrig.png", 0, 0, -4, 0.4f, false));
                    originals.add(new Entity("l2/PolloOrig.png", 0, 0, -5, 0.4f, false));
                    originals.add(new Entity("l2/PyyOrig.png", 0, 0, -6, 0.4f, false));
                    originals.add(new Entity("l2/KettuOrig.png", 0, 0, -7, 0.4f, false));
                    originals.add(new Entity("l2/NaataOrig.png", 0, 0, -8, 0.4f, false));
                    originals.add(new Entity("l2/TiainenOrig.png", 0, 0, -9, 0.4f, false));
                    originals.add(new Entity("l2/TulkkuOrig.png", 0, 0, -10, 0.4f, false));

                    silhouettes.add(new Entity("l2/HiiriSil.png", 70f, 310f, -1, 0.4f, false));
                    silhouettes.add(new Entity("l2/JanisSil.png", 200f, 310f, -2, 0.4f, false));
                    silhouettes.add(new Entity("l2/KorppiSil.png", 300f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity("l2/LumikkoSil.png", 430f, 310f, -4, 0.4f, false));
                    silhouettes.add(new Entity("l2/PolloSil.png", 540f, 310f, -5, 0.4f, false));
                    silhouettes.add(new Entity("l2/PyySil.png", 70f, 120f, -6, 0.4f, false));
                    silhouettes.add(new Entity("l2/KettuSil.png", 190f, 120f, -7, 0.4f, false));
                    silhouettes.add(new Entity("l2/NaataSil.png", 320f, 120f, -8, 0.4f, false));
                    silhouettes.add(new Entity("l2/TiainenSil.png", 440f, 120f, -9, 0.4f, false));
                    silhouettes.add(new Entity("l2/TulkkuSil.png", 550f, 120f, -10, 0.4f, false));
                    break;

                case 3:
                    entities.add(new Entity("l3/myyraEnt.png", 355f, 12f, -1, 0.65f, false));
                    entities.add(new Entity("l3/sorsaEnt.png", 592f, 15f, -2, 0.7f, false));
                    entities.add(new Entity("l3/supikoiraEnt.png", 251f, 93f, -3, 0.7f, false));
                    entities.add(new Entity("l3/talitinttiEnt.png", 440f, 427f, -4, 0.6f, false));
                    entities.add(new Entity("l3/tikkaEnt.png", 63f, 320f, -5, 0.60f, false));
                    entities.add(new Entity("l3/yokkonenEnt.png", 735f, 145f, -6, 0.55f, false));
                    entities.add(new Entity("l3/haperoEnt.png", 100f, 110f, -7, 0.55f, false));
                    entities.add(new Entity("l3/karpassieniEnt.png", 190f, 20f, -8, 0.55f, false));
                    entities.add(new Entity("l3/tattiEnt.png", 58f, 78f, -9, 0.55f, false));
                    entities.add(new Entity("l3/uikkuEnt.png", 645f, 120f, -10, 0.65f, false));

                    originals.add(new Entity("l3/myyraOrig.png", 0, 0, -1, 0.4f, false));
                    originals.add(new Entity("l3/sorsaOrig.png", 0, 0, -2, 0.4f, false));
                    originals.add(new Entity("l3/supikoiraOrig.png", 0, 0, -3, 0.4f, false));
                    originals.add(new Entity("l3/talitinttiOrig.png", 0, 0, -4, 0.5f, false));
                    originals.add(new Entity("l3/tikkaOrig.png", 0, 0, -5, 0.5f, false));
                    originals.add(new Entity("l3/yokkonenOrig.png", 0, 0, -6, 0.3f, false));
                    originals.add(new Entity("l3/haperoOrig.png", 0, 0, -7, 0.3f, false));
                    originals.add(new Entity("l3/karpassieniOrig.png", 0, 0, -8, 0.3f, false));
                    originals.add(new Entity("l3/tattiOrig.png", 0, 0, -9, 0.3f, false));
                    originals.add(new Entity("l3/uikkuOrig.png", 0, 0, -10, 0.6f, false));

                    silhouettes.add(new Entity("l3/myyraSil.png", 70f, 310f, -1, 0.4f, false));
                    silhouettes.add(new Entity("l3/sorsaSil.png", 190f, 310f, -2, 0.4f, false));
                    silhouettes.add(new Entity("l3/supikoiraSil.png", 340f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity("l3/talitinttiSil.png", 450f, 310f, -4, 0.5f, false));
                    silhouettes.add(new Entity("l3/tikkaSil.png", 580f, 310f, -5, 0.5f, false));
                    silhouettes.add(new Entity("l3/yokkonenSil.png", 70f, 120f, -6, 0.3f, false));
                    silhouettes.add(new Entity("l3/haperoSil.png", 190f, 120f, -7, 0.3f, false));
                    silhouettes.add(new Entity("l3/karpassieniSil.png", 330f, 120f, -8, 0.3f, false));
                    silhouettes.add(new Entity("l3/tattiSil.png", 460f, 120f, -9, 0.3f, false));
                    silhouettes.add(new Entity("l3/uikkuSil.png", 560f, 120f, -10, 0.6f, false));
                    break;

                case 4:
                    entities.add(new Entity("l4/haikaraEnt.png", 350f, 300f, -1, 0.75f, false));
                    entities.add(new Entity("l4/harmaalokkiEnt.png", 500f, 330f, -2, 0.75f, false));
                    entities.add(new Entity("l4/haukiEnt.png", 530f, 50f, -3, 0.75f, false));
                    entities.add(new Entity("l4/kerttunenEnt.png", 100f, 220f, -4, 0.75f, false));
                    entities.add(new Entity("l4/korentoEnt.png", 270f, 240f, -5, 0.75f, false));
                    entities.add(new Entity("l4/lokkiEnt.png", 400f, 430f, -6, 0.75f, false));
                    entities.add(new Entity("l4/myyraEnt.png", 265f, 90f, -7, 0.75f, false));
                    entities.add(new Entity("l4/sorsaEnt.png", 500f, 200f, -8, 0.75f, false));
                    entities.add(new Entity("l4/vastarakkiEnt.png", 190f, 40f, -9, 0.75f, false));
                    entities.add(new Entity("l4/sammakkoEnt.png", 265f, 180f, -10, 0.75f, false));

                    originals.add(new Entity("l4/haikaraOrig.png", 0, 0, -1, 0.2f, false));
                    originals.add(new Entity("l4/harmaalokkiOrig.png", 0, 0, -2, 0.3f, false));
                    originals.add(new Entity("l4/haukiOrig.png", 0, 0, -3, 0.4f, false));
                    originals.add(new Entity("l4/kerttunenOrig.png", 0, 0, -4, 0.15f, false));
                    originals.add(new Entity("l4/korentoOrig.png", 0, 0, -5, 0.2f, false));
                    originals.add(new Entity("l4/lokkiOrig.png", 0, 0, -6, 0.2f, false));
                    originals.add(new Entity("l4/myyraOrig.png", 0, 0, -7, 0.2f, false));
                    originals.add(new Entity("l4/sorsaOrig.png", 0, 0, -8, 0.2f, false));
                    originals.add(new Entity("l4/vastarakkiOrig.png", 0, 0, -9, 0.2f, false));
                    originals.add(new Entity("l4/sammakkoOrig.png", 0, 0, -10, 0.2f, false));

                    silhouettes.add(new Entity("l4/haikaraSil.png", 80, 270f, -1, 0.2f, false));
                    silhouettes.add(new Entity("l4/harmaalokkiSil.png", 180f, 310f, -2, 0.3f, false));
                    silhouettes.add(new Entity("l4/haukiSil.png", 320f, 310f, -3, 0.4f, false));
                    silhouettes.add(new Entity("l4/kerttunenSil.png", 460f, 310f, -4, 0.15f, false));
                    silhouettes.add(new Entity("l4/korentoSil.png", 560f, 310f, -5, 0.2f, false));
                    silhouettes.add(new Entity("l4/lokkiSil.png", 80f, 130f, -6, 0.2f, false));
                    silhouettes.add(new Entity("l4/myyraSil.png", 200f, 130f, -7, 0.2f, false));
                    silhouettes.add(new Entity("l4/sorsaSil.png", 320f, 130f, -8, 0.2f, false));
                    silhouettes.add(new Entity("l4/vastarakkiSil.png", 470f, 130f, -9, 0.2f, false));
                    silhouettes.add(new Entity("l4/sammakkoSil.png", 560f, 130f, -10, 0.2f, false));
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
