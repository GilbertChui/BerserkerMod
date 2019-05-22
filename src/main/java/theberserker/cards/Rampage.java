package theberserker.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
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

public class Rampage extends CustomCard{
  public static final String ID = "theBerserker:Rampage";
  public static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final int DAMAGE = 6;
  public static final int UPGRADE_PLUS_DAMAGE = 2;
  public static final int COST = 0;
  public static final int SELF_DMG = 3;
  
  public Rampage() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
    this.magicNumber = this.baseMagicNumber = SELF_DMG;
    this.damage = this.baseDamage = DAMAGE;
  }
  
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager
    .addToBottom(new LoseHPAction(p, p, magicNumber));
    AbstractDungeon.actionManager
    .addToBottom(new DrawCardAction(p, 1));
    AbstractDungeon.actionManager
    .addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HEAVY));
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new Rampage();
  }
}
