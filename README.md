# Grappling Hook - Get Over Here! (Bukkit / Spigot)

Wouldn't minecraft be *awesome* if you could grapple other players, or even scale buildings / terrain with the toss of a hook, and pull? **Now you can!**

**Features**:
* Pull yourself to locations
* Pull entities (mobs, or items) to you
* Extremely lightweight plugin. Won't lag your server *at all*.
* Features an API for other plugins to operate on!
* No permissions required (but are supported, if you want them)
* No configuration requried! (Though, you can configure the plugin)
* Totally plug and play!

**Plugin Commands and Permissions**:
```
/gh       ||   grapplinghook.give
/gh get   ||   grapplinghook.get
/gh give  ||   grapplinghook.give
```

**Installation**
(Note: *Requires [Commons](http://www.github.com/TechnicalBro/Commons) as a Dependency*)

1. Download Commons
   * Place it in your plugins folder
   * Configure it as you desire (After server has been started & stopped, once, to generate configuration file).
2. Download / Compile Grappling Hook
3. Place it into your plugins folder
4. Start your server
5. PLAY AWAY! It's ready to go!
6. (Optional) Stop server, configure Grappling Hook, start server again!
7. Give a Star here and review on Spigot?

**How to use the API**:
```java
//Import the API into your class
import com.caved_in.grapplinghook.api.HookAPI;
//Some more code here...

//Check if an ItemStack is a Grappling Hook
HookAPI.isGrapplingHook(item);

//Create a grappling hook!
ItemStack grapple = HookAPI.createGrapplingHook();

//Play the custom sound effect when a player grapples.
HookAPI.playGrappleSound(location);
```

*Note: If you have any suggestions for features, requests, or problems to report please open an issue, or contact [me](http://www.twitter.com/TechnicalBro) via Twitter!*

**It may be best, if you can code, to submit the requst for change as a pull request.**

Thanks so much, and happy Cdoe!