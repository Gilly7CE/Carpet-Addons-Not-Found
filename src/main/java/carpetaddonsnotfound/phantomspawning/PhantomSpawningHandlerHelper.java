package carpetaddonsnotfound.phantomspawning;

/**
 * Helper class for {@link PhantomSpawningHandler}. Allows callers to use the interface without caring about its
 * implementation.
 *
 * @author Gilly7CE
 * @see PhantomSpawningHandler
 */
public class PhantomSpawningHandlerHelper {
  private static final PhantomSpawningHandler phantomSpawningHandler = createPhantomSpawningHandler();

  /**
   * Returns the {@link PhantomSpawningHandler} for the mod.
   *
   * @return {@link PhantomSpawningHandler}
   */
  public static PhantomSpawningHandler getPhantomSpawningHandler() {
    return phantomSpawningHandler;
  }

  private static PhantomSpawningHandler createPhantomSpawningHandler() {
    DisableForCreativePlayersHandler disableForCreativePlayersHandler = new DisableForCreativePlayersHandler();
    DisableInMushroomFieldsHandler disableInMushroomFieldsHandler = new DisableInMushroomFieldsHandler();
    ObeyHostileMobCapHandler obeyHostileMobCapHandler = new ObeyHostileMobCapHandler();

    disableForCreativePlayersHandler.setNextHandler(disableInMushroomFieldsHandler)
                                    .setNextHandler(obeyHostileMobCapHandler);

    return disableForCreativePlayersHandler;
  }
}
