using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace HeroPickerFront
{
    /// <summary>
    /// Interaction logic for ChampionInfo.xaml
    /// </summary>
    public partial class ChampionInfo : UserControl, INotifyPropertyChanged
    {
        public ChampionInfo()
        {
            InitializeComponent();
            //this.DataContext = this;
        }

        public string ChampionName
        {
            get { return (string)GetValue(ChampionNameProperty); }
            set { SetValue(ChampionNameProperty, value); }
        }

        public static readonly DependencyProperty ChampionNameProperty =
            DependencyProperty.Register("ChampionName", typeof(string), typeof(ChampionInfo), new PropertyMetadata(""));


        public string Position
        {
            get { return (string)GetValue(PositionProperty); }
            set { SetValue(PositionProperty, value); }
        }

        public static readonly DependencyProperty PositionProperty =
            DependencyProperty.Register("Position", typeof(string), typeof(ChampionInfo), new PropertyMetadata(""));
        
        public string StrongPoint
        {
            get { return (string)GetValue(StrongPointProperty); }
            set { SetValue(StrongPointProperty, value); }
        }

        public static readonly DependencyProperty StrongPointProperty =
            DependencyProperty.Register("StrongPoint", typeof(string), typeof(ChampionInfo), new PropertyMetadata(""));
        public string Classes
        {
            get { return (string)GetValue(ClassesProperty); }
            set { SetValue(ClassesProperty, value); }
        }

        public static readonly DependencyProperty ClassesProperty =
            DependencyProperty.Register("Classes", typeof(string), typeof(ChampionInfo), new PropertyMetadata(""));
        public string Counters
        {
            get { return (string)GetValue(CountersProperty); }
            set { SetValue(ClassesProperty, CountersProperty); }
        }

        public static readonly DependencyProperty CountersProperty =
            DependencyProperty.Register("Counters", typeof(string), typeof(ChampionInfo), new PropertyMetadata(""));
        public event PropertyChangedEventHandler PropertyChanged;
        public void NotifyPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }
    }
}
