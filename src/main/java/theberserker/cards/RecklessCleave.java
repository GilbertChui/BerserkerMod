package theberserker.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class RecklessCleave extends CustomCard {

  public static final String ID = "theBerserker:RecklessCleave";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int SELF_DMG = 5;
  private static final int COST = 1;
  private static final int ATTACK_DMG = 5;
  private static final int UPGRADE_PLUS_DMG = 3;

  public RecklessCleave() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
    this.magicNumber = this.baseMagicNumber = SELF_DMG;
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
        .addToBottom(new DamageAction(p, new DamageInfo(p, magicNumber, this.damageTypeForTurn)));
    for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
      AbstractDungeon.actionManager
          .addToBottom(new DamageAction(mo, new DamageInfo(p, this.damage, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
    AbstractDungeon.actionManager
        .addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
  }

  @Override
  public AbstractCard makeCopy() {
    return new RecklessCleave();
  }
}
