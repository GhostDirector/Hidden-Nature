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
                    entities.add(new Entity("hirvi2.png", 279f, 78f, -1, 1f, false));
                    entities.add(new Entity("orava2.png", 90f, 360f, -2, 1f, false));
                    entities.add(new Entity("siili2.png", 535f, 5f, -3, 1f, false));
                    entities.add(new Entity("tintti2.png", 190f, 398f, -4, 1f, false));

                    originals.add(new Entity("hirvi.png", 0, 0, -1, 1f, false));
                    originals.add(new Entity("orava.png", 0, 0, -2, 1f, false));
                    originals.add(new Entity("HEDGEHOG.png", 0, 0, -3, 0.15f, false));
                    originals.add(new Entity("TIT.png", 0, 0, -4, 0.12f, false));

                    silhouettes.add(new Entity("hirviSil.png", 10f, 10f, -1, 1f, true));
                    silhouettes.add(new Entity("oravaSil.png", 150f, 10f, -2, 1f, true));
                    silhouettes.add(new Entity("siiliSil.png", 250f, 10f, -3, 1.14f, true));
                    silhouettes.add(new Entity("tinttiSil.png", 350f, 10f, -4, 1.45f, true));
                    break;

                case 2:
                    entities.add(new Entity("doge.png", 300f, 200f, -1, 1f, false));

                    originals.add(new Entity("doge.png", 0, 0, -1, 1f, false));

                    silhouettes.add(new Entity("dogesilu.png", 10f, 10f, -1, 1f, true));
                    break;

                case 3:
                    entities.add(new Entity("doge.png", 250f, 150f, -1, 1f, false));

                    originals.add(new Entity("doge.png", 0, 0, -1, 1f, false));

                    silhouettes.add(new Entity("dogesilu.png", 10f, 10f, -1, 1f, true));
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
