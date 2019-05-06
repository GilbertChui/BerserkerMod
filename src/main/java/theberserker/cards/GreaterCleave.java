package theberserker.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class GreaterCleave extends CustomCard{
  public static final String ID = "GreaterCleave";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int COST = 1;
  private static final int ATTACK_DMG = 4;
  private static final int UPGRADE_PLUS_DMG = 2;
  
  public GreaterCleave() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
    this.damage = this.baseDamage = ATTACK_DMG;
    this.damageType = DamageInfo.DamageType.NORMAL;
  }
  
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeDamage(UPGRADE_PLUS_DMG);
    }

  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    //TODO: fix this.
    //p, this.damage, new DamageInfo(p, this.damage, this.damageTypeForTurn),
    //AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, false
    AbstractDungeon.actionManager
        .addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageType, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    AbstractDungeon.actionManager
        .addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageType, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new GreaterCleave();
  }
}
