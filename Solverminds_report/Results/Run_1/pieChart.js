$(document).ready(function() {
    var data = [['Passed', 3], ['Failed', 1], ['Skipped', 0]];
    jQuery.jqplot('chart', [data],
            {seriesColors: ["#7BB661", "#E03C31", "#21ABCD"],
                seriesDefaults: {
                    // Make this a pie chart.
                    renderer: jQuery.jqplot.PieRenderer,
                    rendererOptions: {
                        padding: 15,
                        sliceMargin: 1,
                        // Put data labels on the pie slices.
                        // By default, labels show the percentage of the slice.
                        showDataLabels: true
                    }
                },
                grid: {borderColor: '#cccccc', background: '#ffffff',
                    borderWidth: 0, // pixel width of border around grid.
                    shadow: false // draw a shadow for grid.
                },
                legend: {show: true, location: 'e'}
            }
    );
});
