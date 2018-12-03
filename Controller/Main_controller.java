package clef.Controller;

import clef.Dao.Dao_clef;
import clef.View.View_console;

import java.util.Iterator;

public class Main_controller
{
    public static void main( String[] args )
    {
        // view clef
        View_console vc = new View_console();

        // controller gestion_clefs
        Dao_clef gc = new Dao_clef();

        // saisie
        Iterator newClef;
        Iterator updateClef;
        Iterator searchClef;
        int saisieMenu;
        String id;

        // variable de sortie
        boolean exit = false;

        do
        {
            saisieMenu = vc.mainMenu();

            switch ( saisieMenu )
            {
                // Ajouter un clé
                case 1 :
                    newClef = vc.add();
                    gc.add( newClef );
                    break;

                // Supprimer une clé
                case 2 :
                    // si il existe au moins une clé
                    if ( gc.getFirstClefs() != null )
                    {
                        id = vc.remove();
                        gc.remove(id);
                    }
                    else
                    {
                        System.out.println( "Aucune clé existe." );
                    }
                    break;

                // modifier une clé
                case 3 :
                    // si il existe au moins une clé
                    if ( gc.getFirstClefs() != null )
                    {
                        updateClef = vc.update( gc );

                        // ne lance pas l'update si la saisie échoue
                        if ( updateClef.hasNext() )
                        {
                            gc.update( updateClef );
                        }
                    }
                    else
                    {
                        System.out.println( "Aucune clé existe." );
                    }
                    break;

                // recherche
                case 4 :
                    searchClef = vc.search();
                    gc.search( searchClef );
                    break;

                // liste complète
                case 5 :
                    // si il existe au moins une clé
                    if ( gc.getFirstClefs() != null )
                    {
                        gc.liste();
                    }
                    else
                    {
                        System.out.println( "Aucune clé existe." );
                    }
                    break;

                // afficher une clé
                case 6 :
                    // si il existe au moins une clé
                    if ( gc.getFirstClefs() != null )
                    {
                        id = vc.listOneKey();
                        gc.uneClef(id);
                    }
                    else
                    {
                        System.out.println( "Aucune clé existe." );
                    }
                    break;

                // sortie
                case 7 :
                    exit = true;
                    break;

                // erreur
                default:
                    System.out.println( "La saisie est invalide." );
            }
        } while ( !exit );
    }
}