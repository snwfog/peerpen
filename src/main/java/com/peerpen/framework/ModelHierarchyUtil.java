package com.peerpen.framework;

import com.google.common.collect.Maps;
import com.sunnyd.Base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelHierarchyUtil {

    static final Logger logger = LoggerFactory.getLogger( ModelHierarchyUtil.class );

    public static boolean isNumeric( String str ) {
        return str.matches( "-?\\d+(\\.\\d+)?" );
    }

    // TODO: Bake this into PPAR
    public static Map<String, Base> parameterAsMap( Map<String, Object> map ) {
        Map<String, Base> baseMap = Maps.newLinkedHashMap();
        for ( String key : map.keySet() ) {
            Object value = map.get( key );

            if ( value != null && value instanceof String && isNumeric( (String) value ) ) {
                // Get the object from database;
                try {
                    Base model = forName( key, Integer.parseInt( (String) value ) );
                    baseMap.put( key, model );
                    if ( model == null ) {
                        logger.warn( "Could not find an object for " + key + " of id " + value );
                    }
                } catch ( ClassNotFoundException | NoSuchMethodException
                        | IllegalAccessException | InvocationTargetException | InstantiationException e ) {
                    logger.error( "Failed to fetch object for " + key + " of id " + value );
                }

            }
        }

        return baseMap;
    }

    private static <T extends Base> T forName( String className, int id )
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        // className will be in lower case
        Class<?> klazz = Class.forName( "com.peerpen.model." + StringUtils.capitalise( className ) );
        Constructor<?> cons = klazz.getConstructor( );
        T instance = (T) cons.newInstance();
        return instance.find( id );
    }
}
