var pts = 'M-45.2933,169.037 L22.3215,187.154 L24.9365,177.395 L44.4552,172.165 L47.0702,162.406 L32.7815,148.117 L38.0115,128.598 L57.5303,123.368 L71.819,137.657 L66.5889,157.176 L47.0702,162.406 L44.4552,172.165 L58.7439,186.454 L56.1289,196.213 L123.744,214.33 L169.037,45.2933 L101.422,27.176 L98.8072,36.9354 L79.2885,42.1654 L76.6735,51.9247 L90.9622,66.2134 L85.7321,85.7321 L66.2134,90.9622 L51.9247,76.6735 L57.1548,57.1548 L76.6735,51.9247 L79.2885,42.1654 L64.9998,27.8767 L67.6148,18.1173 L1.42109e-14,-1.06581e-14 Z'; var polys = {
	"0" : "M-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L64.9998,27.8767 L74.8993,37.7762 L57.1548,57.1548 L51.9247,76.6735 L66.2134,90.9622 L85.7321,85.7321 L155.177,41.5796 L169.037,45.2933 L162.969,67.94 L38.0115,128.598 L32.7815,148.117 L47.0702,162.406 L44.4552,172.165 L24.9365,177.395 L22.3215,187.154 Z",
	"1" : "M22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L24.9365,177.395 Z",
	"10" : "M47.0702,162.406 L66.5889,157.176 L144.664,136.255 L123.744,214.33 L64.5807,198.478 L58.7439,186.454 L44.4552,172.165 Z",
	"11" : "M44.4552,172.165 L47.0702,162.406 L66.5889,157.176 L155.124,97.2181 L123.744,214.33 L73.0326,200.742 L58.7439,186.454 Z",
	"12" : "M58.7439,186.454 L44.4552,172.165 L47.0702,162.406 L66.5889,157.176 L101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 Z",
	"13" : "M56.1289,196.213 L58.7439,186.454 L101.422,27.176 L169.037,45.2933 L123.744,214.33 Z",
	"14" : "M123.744,214.33 L56.1289,196.213 L58.7439,186.454 L44.4552,172.165 L47.0702,162.406 L66.5889,157.176 L71.819,137.657 L-6.06815,22.6467 L1.42109e-14,-1.06581e-14 L13.8599,3.71374 L51.9247,76.6735 L66.2134,90.9622 L85.7321,85.7321 L90.9622,66.2134 L85.2842,40.5588 L98.8072,36.9354 L101.422,27.176 L169.037,45.2933 Z",
	"15" : "M169.037,45.2933 L123.744,214.33 L56.1289,196.213 L58.7439,186.454 L48.8444,176.554 L66.5889,157.176 L71.819,137.657 L57.5303,123.368 L38.0115,128.598 L-31.4335,172.751 L-45.2933,169.037 L-39.2252,146.39 L85.7321,85.7321 L90.9622,66.2134 L76.6735,51.9247 L79.2885,42.1654 L98.8072,36.9354 L101.422,27.176 Z",
	"16" : "M101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 L98.8072,36.9354 Z",
	"17" : "M98.8072,36.9354 L101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 L90.9622,66.2134 L76.6735,51.9247 L79.2885,42.1654 Z",
	"18" : "M79.2885,42.1654 L98.8072,36.9354 L118.326,31.7053 L169.037,45.2933 L137.657,162.406 L90.9622,66.2134 L76.6735,51.9247 Z",
	"19" : "M76.6735,51.9247 L79.2885,42.1654 L98.8072,36.9354 L109.874,29.4407 L169.037,45.2933 L148.117,123.368 L90.9622,66.2134 Z",
	"2" : "M24.9365,177.395 L22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L32.7815,148.117 L47.0702,162.406 L44.4552,172.165 Z",
	"20" : "M90.9622,66.2134 L76.6735,51.9247 L79.2885,42.1654 L98.8072,36.9354 L101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 L85.7321,85.7321 Z",
	"21" : "M85.7321,85.7321 L90.9622,66.2134 L101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 L71.8189,137.657 L57.5303,123.368 L38.0115,128.598 L-15.8035,176.939 L-45.2933,169.037 L-31.3801,117.112 L66.2134,90.9622 Z",
	"22" : "M66.2134,90.9622 L85.7321,85.7321 L163.807,64.812 L123.744,214.33 L79.6035,202.503 L71.819,137.657 L57.5303,123.368 L38.0115,128.598 L-1.15313,180.864 L-45.2933,169.037 L-5.23002,19.5187 L51.9247,76.6735 Z",
	"23" : "M51.9247,76.6735 L66.2134,90.9622 L137.657,162.406 L123.744,214.33 L94.2539,206.429 L71.819,137.657 L57.5303,123.368 L38.0115,128.598 L22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L57.1548,57.1548 Z",
	"24" : "M57.1548,57.1548 L51.9247,76.6735 L22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L64.9998,27.8767 L79.2885,42.1654 L76.6735,51.9247 Z",
	"25" : "M76.6735,51.9247 L57.1548,57.1548 L-20.9201,78.0749 L1.42109e-14,-1.06581e-14 L59.163,15.8527 L64.9998,27.8767 L79.2885,42.1654 Z",
	"26" : "M79.2885,42.1654 L76.6735,51.9247 L57.1548,57.1548 L-31.3801,117.112 L1.42109e-14,-1.06581e-14 L50.7111,13.588 L64.9998,27.8767 Z",
	"27" : "M64.9998,27.8767 L79.2885,42.1654 L76.6735,51.9247 L57.1548,57.1548 L22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 Z",
	"28" : "M67.6148,18.1173 L64.9998,27.8767 L22.3215,187.154 L-45.2933,169.037 L-8.33333e-08,3.11004e-07 Z",
	"29" : "M1.42109e-14,-1.06581e-14 L67.6148,18.1173 L64.9998,27.8767 L79.2885,42.1654 L76.6735,51.9247 L57.1548,57.1548 L51.9247,76.6735 L129.812,191.684 L123.744,214.33 L109.884,210.617 L71.819,137.657 L57.5303,123.368 L38.0115,128.598 L32.7815,148.117 L38.4594,173.772 L24.9365,177.395 L22.3215,187.154 L-45.2933,169.037 Z",
	"3" : "M44.4552,172.165 L24.9365,177.395 L5.41777,182.625 L-45.2933,169.037 L-13.9132,51.9247 L32.7815,148.117 L47.0702,162.406 Z",
	"4" : "M47.0702,162.406 L44.4552,172.165 L24.9365,177.395 L13.8696,184.89 L-45.2933,169.037 L-24.3732,90.9622 L32.7815,148.117 Z",
	"5" : "M32.7815,148.117 L47.0702,162.406 L44.4552,172.165 L24.9365,177.395 L22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L38.0115,128.598 Z",
	"6" : "M38.0115,128.598 L32.7815,148.117 L22.3215,187.154 L-45.2933,169.037 L1.42109e-14,-1.06581e-14 L67.6148,18.1173 L51.9247,76.6735 L66.2134,90.9622 L85.7321,85.7321 L139.547,37.3916 L169.037,45.2933 L155.124,97.2181 L57.5303,123.368 Z",
	"7" : "M57.5303,123.368 L38.0115,128.598 L-40.0633,149.518 L1.42109e-14,-1.06581e-14 L44.1402,11.8273 L51.9247,76.6735 L66.2134,90.9622 L85.7321,85.7321 L124.897,33.466 L169.037,45.2933 L128.974,194.812 L71.819,137.657 Z",
	"8" : "M71.819,137.657 L57.5303,123.368 L-13.9132,51.9247 L1.42109e-14,-1.06581e-14 L29.4898,7.90177 L51.9247,76.6735 L66.2134,90.9622 L85.7321,85.7321 L101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 L66.5889,157.176 Z",
	"9" : "M66.5889,157.176 L71.819,137.657 L101.422,27.176 L169.037,45.2933 L123.744,214.33 L56.1289,196.213 L58.7439,186.454 L44.4552,172.165 L47.0702,162.406 Z"
};