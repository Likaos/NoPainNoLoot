package fr.minekahest;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {
	
	@EventHandler
	//Cet event permet de g�rer la mort d'une entit�, via filtre il est facile de retrouver
	//Qui a fait quoi, ici on s'int�resse � toutes les morts qui ne concernent que les non joueurs
	//Et on s'assure que �a soit bien un joueur qui ai achev� la cr�ature.
	public void onEntityDeath (EntityDeathEvent event) {
	    Entity entity = event.getEntity();
	    //On v�rifie si l'entit� morte ne soit pas un joueur
	    if ( ! (entity instanceof Player)) {
		    //On v�rifie que la mort soit bien caus�e par une entit�
	    	if (entity.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
	    		Entity killer = ((EntityDamageByEntityEvent)event.getEntity().getLastDamageCause()).getDamager();
	    		//Si cette les d�g�ts ne proviennent pas d'un joueur
	    		//Ni d'une fl�che, ni d'une potion lanc�e
	    			if ( ! (killer instanceof Player) && ! (killer instanceof Arrow) && ! (killer instanceof ThrownPotion)) {
	    				//Ici il est posible de v�rifier si le projectile vient d'un joueur
	    				//Mais je trouve le friendlyfire plus amusant, c'est une technique de combat
	    				//Et cela m�rite un loot ;).
	    				
	    				//Suppresion des loots et de l'xp.
	    		    	event.getDrops().clear();
	    		    	event.setDroppedExp(0);
	    			}
	    	//Si c'est un joueur on peut eventuellement coder ici les actions � r�aliser (autre plugin � venir).
		    }
	    }
	}
}
