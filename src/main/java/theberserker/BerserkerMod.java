package theberserker;

import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import theberserker.cards.*;
import theberserker.characters.TheBerserker;
import theberserker.patches.AbstractCardEnum;
import theberserker.patches.TheBerserkerEnum;
import theberserker.relics.Rock;

@SpireInitializer
public class BerserkerMod implements EditStringsSubscriber, EditCardsSubscriber,
    EditCharactersSubscriber, EditRelicsSubscriber {

  // create a logger for debugging purposes
  Logger logger = LogManager.getLogger(BerserkerMod.class.getName());

  // get the paths to the images of the card backgrounds
  private static final String ATTACK_ORANGE = makePath("512/bg_attack_orange.png");
  private static final String SKILL_ORANGE = makePath("512/bg_skill_orange.png");
  private static final String POWER_ORANGE = makePath("512/bg_power_orange.png");
  private static final String ORB_ORANGE = makePath("512/card_orange_orb.png");
  private static final String CARD_ENERGY_ORB = makePath("512/card_orange_small_orb.png");
  
  private static final String ATTACK_ORANGE_PORTRAIT = makePath("1024/bg_attack_orange.png");
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

    BaseMod.subscribe(this);
    
    logger.info("creating color " + AbstractCardEnum.ORANGE.toString());
    
    BaseMod.addColor(AbstractCardEnum.ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE,
        ORANGE, ATTACK_ORANGE, SKILL_ORANGE, POWER_ORANGE, ORB_ORANGE, ATTACK_ORANGE_PORTRAIT,
        SKILL_ORANGE_PORTRAIT, POWER_ORANGE_PORTRAIT, ORB_ORANGE_PORTRAIT,CARD_ENERGY_ORB);
  }

  public static void initialize() {
    new BerserkerMod();
  }

  @Override
  public void receiveEditStrings() {
    
    logger.info("begin editting strings");
    logger.info("adding card strings for " + TheBerserkerEnum.THE_BERSERKER.toString());
    
    String cardStrings = Gdx.files.internal("localization/BerserkerModCardStrings.json")
        .readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    String powerStrings = Gdx.files.internal("localization/BerserkerModPowerStrings.json")
        .readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
    String relicStrings = Gdx.files.internal("localization/BerserkerModRelicStrings.json")
        .readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

    logger.info("finished editting strings");
  }

  public static final String makePath(String path) {
    return BERSERKER_MOD_ASSETS_FOLDER + "/" + path;
  }

  public static final String makeCardPath(String path) {
    return BERSERKER_MOD_ASSETS_FOLDER + "/cards/" + path;
  }

  public static final Texture makePowerTexture(String path) {
    return new Texture(BERSERKER_MOD_ASSETS_FOLDER + "/powers/" + path);
  }

  public static final Texture getPlaceholderPowerTexture() {
    return new Texture(BERSERKER_MOD_ASSETS_FOLDER + "/powers/placeholderPower32.png");
  }

  @Override
  public void receiveEditRelics() {
    logger.info("Adding relics for " + TheBerserkerEnum.THE_BERSERKER.toString());
    // starter relic
    BaseMod.addRelicToCustomPool(new Rock(), AbstractCardEnum.ORANGE);
    
    // common
    
    // uncommon
    
    // rare
  }

  @Override
  public void receiveEditCards() {
    logger.info("begin editting cards");
    logger.info("adding cards for " + TheBerserkerEnum.THE_BERSERKER.toString());

    // Basic Cards
    BaseMod.addCard(new StrikeB());
    BaseMod.addCard(new Regeneration());
    BaseMod.addCard(new RecklessCleave());

    // TESTCARD
    BaseMod.addCard(new TestCard());

    // Common
    //attacks
    BaseMod.addCard(new GreaterCleave());
    BaseMod.addCard(new Rampage());
    //skills
    BaseMod.addCard(new Axetion());
    BaseMod.addCard(new Medkit());
    BaseMod.addCard(new AxeAQuestion());

    // Uncommon
    //attacks
    BaseMod.addCard(new DoubleEdge());
    //skills
    BaseMod.addCard(new NoPainNoGain());
    BaseMod.addCard(new QuickFix());
    BaseMod.addCard(new JaxPremium());
    BaseMod.addCard(new Sacrifice());

    // Rare
    //attacks
    BaseMod.addCard(new Revenge());
    //skills
    BaseMod.addCard(new Panic());
    BaseMod.addCard(new Ritual());
    //powers
    BaseMod.addCard(new WhirlwindOfDeath());


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
