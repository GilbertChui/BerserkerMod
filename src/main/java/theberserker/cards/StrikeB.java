package theberserker.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class StrikeB extends CustomCard {

  public static final String ID = "theBerserker:StrikeB";
  public static final String NAME = "Strike";
  public static final String DESCRIPTION = "Deal !D! damage";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 6;
  private static final int UPGRADE_PLUS_DMG = 2;

  //TODO: flag this as a strike
  public StrikeB() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);

    this.damage = this.baseDamage = ATTACK_DMG;
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
    AbstractDungeon.actionManager
        .addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

  }

  @Override
  public AbstractCard makeCopy() {
    return new StrikeB();
  }
}
