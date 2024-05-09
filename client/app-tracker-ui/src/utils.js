export function getOption(selectEle) {
    const options = selectEle.options;
    const idx = selectEle.selectedIndex;
    
    return options[idx].value;
}

export function getTodayString() {
    return new Date().toISOString().substring(0, 10);
}