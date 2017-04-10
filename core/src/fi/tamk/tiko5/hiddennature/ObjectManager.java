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
//        isLoad = false;                       // for adjusting hardcoded coordinates
        id = level.getLevelID();
        
        if (!isLoad) {
            switch (this.id) {

                case 1:
                    entities.add(new Entity("l1/HirviEnt.png", 282f, 78f, -1, 1f, false)); //1
                    entities.add(new Entity("l1/JanisEnt.png", 281f, 30f, -2, 1f, false)); //2
                    entities.add(new Entity("l1/KaapaEnt.png", 15f, 75f, -3, 1f, false)); //3
                    entities.add(new Entity("l1/KiipijaEntOrig.png", 372f, 316f, -4, 1f, false)); //4
                    entities.add(new Entity("l1/LehdokkiEntOrig.png", 487f, 5f, -5, 1f, false)); //5
                    entities.add(new Entity("l1/LiitoOravaEnt.png", 457f, 400f, -6, 1f, false)); //6
                    entities.add(new Entity("l1/OravaEnt.png", 90f, 360f, -7, 1f, false)); //7
                    entities.add(new Entity("l1/PerhonenEntOrig.png", 726f, 17f, -8, 1f, false)); //8
                    entities.add(new Entity("l1/TikkaEntOrig.png", 726f, 323f, -9, 1f, false)); //9
                    entities.add(new Entity("l1/TinttiEnt.png", 190f, 398f, -10, 1f, false)); //10

                    originals.add(new Entity("l1/HirviOrig.png", 0, 0, -1, 1f, false)); //1
                    originals.add(new Entity("l1/JanisOrig.png", 0, 0, -2, 0.4f, false)); //2
                    originals.add(new Entity("l1/KaapaOrig.png", 0, 0, -3, 1.14f, false)); //3
                    originals.add(new Entity("l1/KiipijaEntOrig.png", 0, 0, -4, 1.45f, false)); //4
                    originals.add(new Entity("l1/LehdokkiEntOrig.png", 0, 0, -5, 1.45f, false)); //5
                    originals.add(new Entity("l1/LiitoOravaOrig.png", 0, 0, -6, 1.45f, false)); //6
                    originals.add(new Entity("l1/OravaOrig.png", 0, 0, -7, 1.45f, false)); //7
                    originals.add(new Entity("l1/PerhonenEntOrig.png", 0, 0, -8, 1.45f, false)); //8
                    originals.add(new Entity("l1/TikkaEntOrig.png", 0, 0, -9, 1.45f, false)); //9
                    originals.add(new Entity("l1/TinttiOrig.png", 0, 0, -10, 1.45f, false)); //10

                    silhouettes.add(new Entity("l1/HirviSil.png", 70f, 280f, -1, 1f, true)); //1
                    silhouettes.add(new Entity("l1/JanisSil.png", 220f, 310f, -2, 0.4f, true)); //2
                    silhouettes.add(new Entity("l1/KaapaSil.png", 320f, 310f, -3, 1.14f, true)); //3
                    silhouettes.add(new Entity("l1/KiipijaSil.png", 430f, 310f, -4, 1.45f, true)); //4
                    silhouettes.add(new Entity("l1/LehdokkiSil.png", 510f, 310f, -5, 1.45f, true)); //5
                    silhouettes.add(new Entity("l1/LiitoOravaSil.png", 70f, 120f, -6, 1.45f, true)); //6
                    silhouettes.add(new Entity("l1/OravaSil.png", 180f, 120f, -7, 1.45f, true)); //7
                    silhouettes.add(new Entity("l1/PerhonenSil.png", 320f, 120f, -8, 1.45f, true)); //8
                    silhouettes.add(new Entity("l1/TikkaSil.png", 430f, 120f, -9, 1.45f, true)); //9
                    silhouettes.add(new Entity("l1/TinttiSil.png", 510f, 120f, -10, 1.45f, true)); //10
                    break;

                case 2:
                    entities.add(new Entity("l2/HiiriEnt.png", 740f, 145f, -1, 1f, false));
                    entities.add(new Entity("l2/JanisEnt.png", 85f, 20f, -2, 1f, false));
                    entities.add(new Entity("l2/KorpiEnt.png", 297f, 200f, -3, 1f, false));
                    entities.add(new Entity("l2/LumikkoEnt.png", 670f, 40f, -4, 1f, false));
                    entities.add(new Entity("l2/PolloEnt.png", 123f, 283f, -5, 1f, false));
                    entities.add(new Entity("l2/PyyEnt.png", 197f, 37f, -6, 1f, false));
                    entities.add(new Entity("l2/KettuEnt.png", 302f, 50f, -7, 1f, false));
                    entities.add(new Entity("l2/NaataEnt.png", 667f, 333f, -8, 1f, false));
                    entities.add(new Entity("l2/TiainenEnt.png", 20f, 360f, -9, 1f, false));
                    entities.add(new Entity("l2/TulkkuEnt.png", 677f, 245f, -10, 1f, false));

                    originals.add(new Entity("l2/HiiriOrig.png", 0, 0, -1, 0.1f, false));
                    originals.add(new Entity("l2/JanisOrig.png", 0, 0, -2, 0.1f, false));
                    originals.add(new Entity("l2/KorppiOrig.png", 0, 0, -3, 0.2f, false));
                    originals.add(new Entity("l2/LumikkoOrig.png", 0, 0, -4, 0.05f, false));
                    originals.add(new Entity("l2/PolloOrig.png", 0, 0, -5, 0.2f, false));
                    originals.add(new Entity("l2/PyyOrig.png", 0, 0, -6, 0.2f, false));
                    originals.add(new Entity("l2/KettuOrig.png", 0, 0, -7, 0.2f, false));
                    originals.add(new Entity("l2/NaataOrig.png", 0, 0, -8, 0.2f, false));
                    originals.add(new Entity("l2/TiainenOrig.png", 0, 0, -9, 0.2f, false));
                    originals.add(new Entity("l2/TulkkuOrig.png", 0, 0, -10, 0.2f, false));

                    silhouettes.add(new Entity("l2/HiiriSil.png", 70f, 280f, -1, 0.1f, true));
                    silhouettes.add(new Entity("l2/JanisSil.png", 200f, 310f, -2, 0.1f, true));
                    silhouettes.add(new Entity("l2/KorppiSil.png", 300f, 310f, -3, 0.2f, true));
                    silhouettes.add(new Entity("l2/LumikkoSil.png", 430f, 310f, -4, 0.05f, true));
                    silhouettes.add(new Entity("l2/PolloSil.png", 540f, 310f, -5, 0.2f, true));
                    silhouettes.add(new Entity("l2/PyySil.png", 70f, 120f, -6, 0.2f, true));
                    silhouettes.add(new Entity("l2/KettuSil.png", 180f, 120f, -7, 0.2f, true));
                    silhouettes.add(new Entity("l2/NaataSil.png", 330f, 120f, -8, 0.2f, true));
                    silhouettes.add(new Entity("l2/TiainenSil.png", 450f, 120f, -9, 0.2f, true));
                    silhouettes.add(new Entity("l2/TulkkuSil.png", 540f, 120f, -10, 0.2f, true));
                    break;

                case 3:
                    entities.add(new Entity("doge.png", 80f, 230f, -1, 1f, false));
                    entities.add(new Entity("doge.png", 265f, 120f, -2, 1f, false));
                    entities.add(new Entity("doge.png", 320f, 380f, -3, 1f, false));
                    entities.add(new Entity("diilaa.png", 450f, 320f, -4, 0.48f, false));
                    entities.add(new Entity("fatso.png", 455f, 280f, -5, 3.05f, false));

                    originals.add(new Entity("doge.png", 0, 0, -1, 1f, false));
                    originals.add(new Entity("doge.png", 0, 0, -2, 1f, false));
                    originals.add(new Entity("doge.png", 0, 0, -3, 1f, false));

                    originals.add(new Entity("fatso.png", 0, 0, -5, 3.05f, false));
                    originals.add(new Entity("diilaa.png", 0, 0, -4, 0.48f, false));

                    silhouettes.add(new Entity("dogesilu.png", 460, 310f, -1, 1f, true));
                    silhouettes.add(new Entity("dogesilu.png", 560f, 260f, -2, 1f, true));
                    silhouettes.add(new Entity("dogesilu.png", 500f, 260f, -3, 1f, true));

                    silhouettes.add(new Entity("fatsosilu.png", 510f, 310f, -5, 3.05f, true));
                    silhouettes.add(new Entity("diilaa.png", 505f, 358f, -4, 0.48f, true));
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
