
function checkclicker1(idseria) {
    var series = chara.series[idseria];
    if (checkbox0.checked) {
        series.hide();
    } else {
        series.show();


    }
}
function checkclicker2(idseria) {
    var series = chara.series[idseria];
    if (checkbox1.checked) {
        series.hide();
    } else {
        console.log(checkbox1.checked)
        series.show();
    }
}

function checkclicker3(idseria) {
    var series = chara.series[idseria];
    if (checkbox2.checked) {
        series.hide();
    } else {
        console.log(checkbox2.checked)
        series.show();
    }
}
function draw1(container, data, psp) {
        date = this.dates.split('/'),
        date[3] = parseFloat(date[3]),
        date[1] = parseFloat(date[1]),
        date[2] = parseFloat(date[2]),
        Highcharts.chart(container, {
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: 'Hourly chart'
            },
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: {
                    hour: '%H:%M'
                }
            },
            legend: {
                align: 'right',
                verticalAlign: 'top',
                layout: 'vertical',
                x: 0,
                y: 100,
            },
            yAxis: {
                title: {
                    text: 'Puissance'
                }
            },
            series: [
                {
                    type: 'area',
                    name: 'PA',
                    data: data,
                    pointStart: Date.UTC(date[3], date[2] - 1, date[1]),
                    pointInterval: 60 * 1000 * 10,
                    // lineColor: 'yellow',
                }, {
                    type: 'line',
                    name: 'PS',
                    color: '#FF8C00',
                    data: psp,
                    pointStart: Date.UTC(date[3], date[2] - 1, date[1]),
                    pointInterval: 60 * 1000 * 10
                }]
        })

}