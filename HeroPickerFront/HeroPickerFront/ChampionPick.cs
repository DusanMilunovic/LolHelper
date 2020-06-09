using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HeroPickerFront
{
    class ChampionPick
    {
        public String champion { get; set; }
        public bool myTeam { get; set; }
        public bool myChamp { get; set; }
        public ChampionPick() { }
        public ChampionPick(String championn, bool myTeamm, bool myChampp)
        {
            champion = championn;
            myTeam = myTeamm;
            myChamp = myChampp;
        }
    }
}
