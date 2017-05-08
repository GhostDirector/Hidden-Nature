package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class PrefHandler {
    private Preferences prefs, globalPrefs;
    private Level level;
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private Array<Entity>originals = new Array<Entity>();
    private Entity tmpEnt;
    private HiddenNature hn;

    public PrefHandler(Level l, HiddenNature hiddenNature){
        hn = hiddenNature;
        level = l;
        prefs = Gdx.app.getPreferences("savedLevel"+level.getLevelID());

    }

    public void save(){
        Array<Entity> saveEntities = level.getEntities();
        Array<Entity> saveOriginals = level.getOriginals();
        Array<Entity> saveSilhouettes = level.getSilhouettes();

        String entity = "";
        String original = "";
        String silhouette = "";

        for(int i = 0; i < saveEntities.size; i++){
            entity += saveEntities.get(i).getButtonID() +
                    "::"+ saveEntities.get(i).getPath() +
                    "::"+ saveEntities.get(i).getX() +
                    "::"+ saveEntities.get(i).getY() +
                    "::"+ saveEntities.get(i).getScale() +
                    "::"+ String.valueOf(saveEntities.get(i).isFound()) + ":;:";

        }

        for(int i = 0; i < saveOriginals.size; i++){
            original += saveOriginals.get(i).getButtonID() +
                    "::"+ saveOriginals.get(i).getPath() +
                    "::"+ saveOriginals.get(i).getX() +
                    "::"+ saveOriginals.get(i).getY() +
                    "::"+ saveOriginals.get(i).getScale() +
                    "::"+ String.valueOf(saveOriginals.get(i).isFound()) + ":;:";
        }

        for(int i = 0; i < saveSilhouettes.size; i++){
            silhouette += saveSilhouettes.get(i).getButtonID() +
                    "::"+ saveSilhouettes.get(i).getPath() +
                    "::"+ saveSilhouettes.get(i).getX() +
                    "::"+ saveSilhouettes.get(i).getY() +
                    "::"+ saveSilhouettes.get(i).getScale() +
                    "::"+ String.valueOf(saveSilhouettes.get(i).isFound()) + ":;:";
        }

        prefs.putString("Entity", ""+entity);
        prefs.putString("Original", ""+original);
        prefs.putString("Silhouette", ""+silhouette);
        prefs.flush();
    }

    public boolean loadLevel() {
        boolean isLoad;

        try {
            globalPrefs = Gdx.app.getPreferences("settings");
            prefs = Gdx.app.getPreferences("savedLevel"+level.getLevelID());

            boolean reset = globalPrefs.getBoolean("Reset"+level.getLevelID(), true);

            String entity = prefs.getString("Entity", "true");
            String original = prefs.getString("Original", "true");
            String silhouette = prefs.getString("Silhouette", "true");

            if (Boolean.valueOf(entity) ||
                    Boolean.valueOf(original) ||
                    Boolean.valueOf(silhouette) || reset){

                isLoad = false;


            } else {
                String[] entitiesString = entity.split(":;:");
                String[] originalsString = original.split(":;:");
                String[] silhouettesString = silhouette.split(":;:");

                entities = getArrays(entitiesString);
                originals = getArrays(originalsString);
                silhouettes = getArrays(silhouettesString);

                isLoad = true;
            }

        } catch (Exception e){
            isLoad = false;
        }

        return isLoad;
    }

    public Array<Entity> getArrays(String[] longStrings) {
        Array<Entity>array = new Array<Entity>();

        for(int i = 0; i < longStrings.length; i++){
            String[] shortStrings = longStrings[i].split("::");

            String file = shortStrings[1];
            float x = Float.valueOf(shortStrings[2]);
            float y = Float.valueOf(shortStrings[3]);
            int ID = Integer.valueOf(shortStrings[0]);
            float scale = Float.valueOf(shortStrings[4]);
            boolean found = Boolean.valueOf(shortStrings[5]);

            tmpEnt = new Entity(hn.getAm().get(file, Texture.class), x, y, ID, scale, found);

            array.add(tmpEnt);
        }

        return array;
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public Array<Entity> getOriginals() {
        return originals;
    }

    public Array<Entity> getSilhouettes() {
        return silhouettes;
    }
}