package theberserker;


import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import theberserker.cards.*;
import theberserker.characters.TheBerserker;
import theberserker.patches.AbstractCardEnum;
import theberserker.patches.TheBerserkerEnum;

@SpireInitializer
public class BerserkerMod
    implements EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber {
  
  // create a logger for debugging purposes
  Logger logger = LogManager.getLogger(BerserkerMod.class.getName());
  
  // get the paths to the images of the card backgrounds
  private static final String ATTACK_ORANGE = makePath("512/bg_attack_orange.png");
  private static final String SKILL_ORANGE = makePath("512/bg_skill_orange.png");
  private static final String POWER_ORANGE = makePath("512/bg_power_orange.png");
  private static final String ORB_ORANGE = makePath("512/card_orange_orb.png");

  private static final String ATTACK_ORANGE_PORTRAIT =
      makePath("resources/1024/bg_attack_orange.png");
  private static final String SKILL_ORANGE_PORTRAIT = makePath("1024/bg_skill_orange.png");
  private static final String POWER_ORANGE_PORTRAIT = makePath("1024/bg_power_orange.png");
  private static final String ORB_ORANGE_PORTRAIT = makePath("1024/card_orange_orb.png");
  private static final Color ORANGE = CardHelper.getColor(255, 150, 0);
  private static final String BERSERKER_MOD_ASSETS_FOLDER = "img";

  // berserker assets
  public static final String BERSERKER_PORTRAIT = "charSelect/berserkerPortrait.png";
  public static final String BERSERKER_BUTTON = "charSelect/berserkerButton.png";

  // get the paths to the images of the card art
  public static final String PLACEHOLDER_ART = makePath("cards/placeholder.png");

  public BerserkerMod() {
    // TODO: characters, character animations, cards, relics, keywords
    // note: use savable if you want to save a value in a relic/card ie: like the
    // pen nib
    // Adding orange as a color
    BaseMod.subscribe(this);
    logger.info("creating color " + AbstractCardEnum.ORANGE.toString());
    BaseMod.addColor(AbstractCardEnum.ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE,
        ORANGE, ATTACK_ORANGE, SKILL_ORANGE, POWER_ORANGE, ORB_ORANGE, ATTACK_ORANGE_PORTRAIT,
        SKILL_ORANGE_PORTRAIT, POWER_ORANGE_PORTRAIT, ORB_ORANGE_PORTRAIT);
  }

  public static void initialize() {
    new BerserkerMod();
  }

  @Override
  public void receiveEditStrings() {
    logger.info("begin editting strings");
    logger.info("adding card strings for " + TheBerserkerEnum.THE_BERSERKER.toString());
    String cardStrings = Gdx.files.internal("localization/BerserkerModCardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    
    logger.info("finished editting strings");
  }

  public static final String makePath(String path) {
    return BERSERKER_MOD_ASSETS_FOLDER + "/" + path;
  }

  @Override
  public void receiveEditCards() {
    logger.info("begin editting cards");
    logger.info("adding cards for " + TheBerserkerEnum.THE_BERSERKER.toString());
    //Basic Cards
    BaseMod.addCard(new StrikeB());
    BaseMod.addCard(new Regeneration());
    
    //Skills
    BaseMod.addCard(new NoPainNoGain());
    
    //Attacks
    BaseMod.addCard(new RecklessCleave());
    BaseMod.addCard(new AxeAQuestion());
    
    logger.info("finished editting cards");
  }

  @Override
  public void receiveEditCharacters() {
    logger.info("begin editting characters");
    logger.info("add " + TheBerserkerEnum.THE_BERSERKER.toString());
    BaseMod.addCharacter(new TheBerserker(TheBerserker.NAME), makePath(BERSERKER_BUTTON),
        makePath(BERSERKER_PORTRAIT), TheBerserkerEnum.THE_BERSERKER);
    logger.info("finished editting characters");
  }

}
