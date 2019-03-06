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

public class TestCard extends CustomCard {

  public static final String ID = "the_berserker:TestCard";
  public static final String NAME = "Test Card";
  public static final String DESCRIPTION = "Deal !D! damage";
  private static final int COST = 0;
  private static final int ATTACK_DMG = 100;
  private static final int UPGRADE_PLUS_DMG = 100;

  // TODO: make texture for orb n shit
  public TestCard() {
    super(ID, NAME, BerserkerMod.makePath(BerserkerMod.PLACEHOLDER_ART), COST, DESCRIPTION,
        AbstractCard.CardType.ATTACK, AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY);
    this.damage = this.baseDamage = ATTACK_DMG;
  }

  @Override
  public void upgrade() {
    // TODO Auto-generated method stub
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
    return new TestCard();
  }
}
