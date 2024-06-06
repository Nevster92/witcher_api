package com.witcher.witcher_api.utils;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.lang.reflect.Field;

public class Utils {


    public SqlPrameter sqlUpdateBuilder(Object object, String tableName, int characterId, String primaryKey){
        SqlPrameter resoult = new SqlPrameter();
        String returnSql = "UPDATE "+tableName+" SET ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        // Az objektum mezőinek lekérése
        Field[] fields = object.getClass().getDeclaredFields();

        // Mezők végigmérése
        for (Field field : fields) {
            // Hozzáférés beállítása, hogy elérhessük a private mezőket is
            field.setAccessible(true);

            try {
                // Mező nevének és értékek
                if(field.get(object) != null) {
                    returnSql += field.getName() + "= :"+field.getName()+" ,";
                    parameters.addValue(field.getName(), field.get(object));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // delete the last ","
        returnSql = returnSql.substring(0, returnSql.length() - 1);

        //TODO kiszervezni, vagy felszámolni ezt az egészet....
        returnSql += "WHERE "+primaryKey+" = :characterId";
        parameters.addValue("characterId", characterId);


        resoult.setSqlQuery(returnSql);
        resoult.setParameters(parameters);
        return resoult;
    }


}
