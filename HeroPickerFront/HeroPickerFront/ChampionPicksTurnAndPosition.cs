using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HeroPickerFront
{
    class ChampionPicksTurnAndPosition
    {
        public List<ChampionPick> picks { get; set; }
        public bool myTurn { get; set; }
        public String position { get; set; }

        public ChampionPicksTurnAndPosition(List<ChampionPick> pickss, bool myTurnn, String positionn)
        {
            picks = pickss;
            myTurn = myTurnn;
            position = positionn;
        }

    }
}
